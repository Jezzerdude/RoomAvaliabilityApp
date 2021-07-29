package com.example.roomavaliabilityapp.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roomavaliabilityapp.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding.roomMenu.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToRoomFragment()
            )
        }
        binding.peopleMenu.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToPeopleFragment()
            )
        }

        return binding.root
    }
}