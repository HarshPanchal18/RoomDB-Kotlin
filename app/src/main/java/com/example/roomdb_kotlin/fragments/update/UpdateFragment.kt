package com.example.roomdb_kotlin.fragments.update

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
import androidx.navigation.fragment.navArgs
import com.example.roomdb_kotlin.R
import com.example.roomdb_kotlin.databinding.FragmentUpdateBinding
import com.example.roomdb_kotlin.model.User
import com.example.roomdb_kotlin.viewmodel.UserViewModel
import kotlinx.coroutines.NonDisposableHandle.parent

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.firstNameEt.setText(args.currentUser.firstName)
        binding.lastNameEt.setText(args.currentUser.lastName)
        binding.AgeEt.setText(args.currentUser.age.toString())

        binding.updateButton.setOnClickListener { updateData() }

        return binding.root
    }

    private fun updateData() {
        val firstName = binding.firstNameEt.text.toString()
        val lastName = binding.lastNameEt.text.toString()
        val age = binding.AgeEt.text

        if(inputCheck(firstName, lastName, age)) {
            // create user object
            val updatedUser = User(args.currentUser.id,firstName,lastName,Integer.parseInt(age.toString()))

            // update data of database
            userViewModel.updateUser(updatedUser)

            Toast.makeText(requireContext(),"Successfully Updated Data", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        } else {
            Toast.makeText(requireContext(),"Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}
