package com.example.roomdb_kotlin.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roomdb_kotlin.R
import com.example.roomdb_kotlin.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment2_to_addFragment2)
        }
        return binding.root
    }

}
