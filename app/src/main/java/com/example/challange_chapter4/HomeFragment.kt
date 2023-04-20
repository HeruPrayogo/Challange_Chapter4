package com.example.challange_chapter4

import android.content.Context
import android.content.SharedPreferences
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.RoomDatabase
import com.example.challange_chapter4.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var sharedPreferences: SharedPreferences
    var dbNote: DatabaseNote? = null
    lateinit var adapterNote: AdapterNote
    lateinit var setViewModel: SetViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.tbHome)

        sharedPreferences= requireContext().getSharedPreferences("datauser", Context.MODE_PRIVATE)
        var getUserNama = sharedPreferences.getString("nama", "")
        binding.tbHome.title = "Welcome, $getUserNama"
        dbNote = DatabaseNote.getInstance(requireContext())
        setVM()
        setViewModel = ViewModelProvider(this).get(SetViewModel::class.java)
        setViewModel.getNoteObserver().observe(viewLifecycleOwner){
            adapterNote.setData(it as ArrayList<RoomData>)
        }
        binding.Floatingbtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        binding.Logout.setOnClickListener {
            val logout = sharedPreferences.edit()
            logout.clear()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

    }

    fun dataAsc(){
        GlobalScope.launch {
            var getData = dbNote?.noteDao()?.getDataAsc()
            activity?.runOnUiThread{
                adapterNote = AdapterNote(getData!!)
                binding.con.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.con.adapter = adapterNote
            }
        }
    }

    fun dataDesc(){
        GlobalScope.launch {
            var getData = dbNote?.noteDao()?.getDataDesc()
            activity?.runOnUiThread{
                adapterNote = AdapterNote(getData!!)
                binding.con.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.con.adapter =adapterNote
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.ascending -> {
                dataAsc()
                return true
            }
            R.id.descending -> {
                dataDesc()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setVM(){
        adapterNote = AdapterNote(ArrayList())
        binding.con.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.con.adapter = adapterNote
    }
    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = dbNote?.noteDao()?.getDataAsc()
            activity?.runOnUiThread {
                adapterNote = AdapterNote(data!!)
                binding.con.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.con.adapter = adapterNote
            }
        }
    }

}