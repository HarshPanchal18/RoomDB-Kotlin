package com.example.roomdb_kotlin.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb_kotlin.R
import com.example.roomdb_kotlin.viewmodel.UserViewModel
import com.example.roomdb_kotlin.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment2_to_addFragment2)
        }

        val adapter = ListAdapter()
        binding.userListRecycler.adapter = adapter
        binding.userListRecycler.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.readAllData.observe(viewLifecycleOwner) { user ->
            adapter.setData(user)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete) {
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        AlertDialog.Builder(requireContext())
            .setPositiveButton("Yes") {_,_ ->
                userViewModel.deleteAllUser()
                Toast.makeText(
                    requireContext(),
                    "Successfully wiped: ",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateFragment2_to_listFragment2)
            }
            .setNegativeButton("No") {_,_ ->}
            .setTitle("Delete Everything?")
            .setMessage("Are you sure you want to delete everything?")
            .create()
            .show()
    }

}
