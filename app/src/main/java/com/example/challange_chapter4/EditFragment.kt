package com.example.challange_chapter4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challange_chapter4.databinding.FragmentEditBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel


@Suppress("DEPRECATION")
class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    var dbNote: DatabaseNote? = null
    lateinit  var setViewModel:SetViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbNote = DatabaseNote.getInstance(requireContext())
        setViewModel = ViewModelProvider(this).get(SetViewModel::class.java)
        var getData = arguments?.getSerializable("data") as RoomData
        binding.Update.setOnClickListener {
            GlobalScope.async {
                var judul = binding.judulEdit.text.toString()
                var content = binding.contentEdit.text.toString()
                setViewModel.updateData(RoomData(getData.id, judul, content))
                findNavController().navigate(R.id.action_editFragment_to_homeFragment)
            }
        }
    }

}