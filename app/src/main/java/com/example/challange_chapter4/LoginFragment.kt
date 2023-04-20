package com.example.challange_chapter4

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challange_chapter4.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("datauser", Context.MODE_PRIVATE)
        binding.Login.setOnClickListener {
            var getUserName = sharedPreferences.getString("username", "")
            var getPassword = sharedPreferences.getString("password", "")
            var username = binding.uss.text.toString()
            var password = binding.pass.text.toString()
                if(getUserName.toString() == username && getPassword.toString() == password){
                   findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
        }
        binding.Regist.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registFragment)
        }
    }


}