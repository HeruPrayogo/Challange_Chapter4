package com.example.challange_chapter4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challange_chapter4.databinding.FragmentRegistBinding


class RegistFragment : Fragment() {
    lateinit var binding: FragmentRegistBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences= requireContext().getSharedPreferences("datauser", Context.MODE_PRIVATE)
        binding.buttonRegist.setOnClickListener {
            var getNama = binding.Nama.text.toString()
            var getUserName = binding.Username.text.toString()
            var getPassword = binding.Password.text.toString()

            var addData = sharedPreferences.edit()
            addData.putString("nama", getNama)
            addData.putString("username", getUserName)
            addData.putString("password", getPassword)
            addData.apply()
            findNavController().navigate(R.id.action_registFragment_to_loginFragment)
        }
    }

}