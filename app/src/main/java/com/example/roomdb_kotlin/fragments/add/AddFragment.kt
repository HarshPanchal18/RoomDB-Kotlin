package com.example.roomdb_kotlin.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdb_kotlin.R
import com.example.roomdb_kotlin.data.User
import com.example.roomdb_kotlin.data.UserViewModel
import com.example.roomdb_kotlin.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = binding.firstNameEt.text.toString()
        val lastName = binding.lastNameEt.text.toString()
        val age = binding.AgeEt.text

        if(inputCheck(firstName, lastName,age)) {
            // create user object
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            // add data to database
            userViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully Added Data", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment2_to_listFragment2)
        } else {
            Toast.makeText(requireContext(),"Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}
