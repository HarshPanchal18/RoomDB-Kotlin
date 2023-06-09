package com.example.roomdb_kotlin.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        setHasOptionsMenu(true)

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
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment2)
        } else {
            Toast.makeText(requireContext(),"Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
            .setPositiveButton("Yes") {_,_ ->
                userViewModel.deleteUser(args.currentUser)
                Toast.makeText(
                    requireContext(),
                    "Successfully removed: ${args.currentUser.firstName + " " +args.currentUser.lastName}",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateFragment2_to_listFragment2)
            }
            .setNegativeButton("No") {_,_ ->}
            .setTitle("Delete ${args.currentUser.firstName}?")
            .setMessage("Are you sure you want to delete ${args.currentUser.firstName + " " +args.currentUser.lastName}?")
            .create().show()
    }
}
