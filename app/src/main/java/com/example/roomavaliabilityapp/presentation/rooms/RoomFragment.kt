package com.example.roomavaliabilityapp.presentation.rooms

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomavaliabilityapp.R
import com.example.roomavaliabilityapp.databinding.FragmentRoomBinding
import com.example.roomavaliabilityapp.presentation.adaptor.RoomAdaptor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {
    private lateinit var binding: FragmentRoomBinding
    private val viewModel: RoomViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(inflater, container, false)
        with(viewModel) {
            viewModelState.observe(viewLifecycleOwner, ::viewStateChange)
            getRooms()
        }
        return binding.root
    }


    fun viewStateChange(roomViewModelState: RoomViewmodel.State) {
        when (roomViewModelState) {
            is RoomViewmodel.State.FullScreenLoading -> {
                binding.loadingSpinner.visibility = View.VISIBLE
            }
            is RoomViewmodel.State.FullScreenError -> {
                binding.loadingSpinner.visibility = View.GONE
                displayErrorDialog()
            }
            is RoomViewmodel.State.Content -> {
                binding.roomList.adapter = RoomAdaptor(roomViewModelState.rooms)
                binding.roomList.layoutManager = LinearLayoutManager(context)
                binding.loadingSpinner.visibility = View.GONE
            }
        }
    }

    fun displayErrorDialog() {
        val errorDialog = AlertDialog.Builder(context)
            .setTitle(R.string.alert_title)
            .setMessage(R.string.alert_message)
            .setPositiveButton(R.string.go_back) { _, _ ->
                activity?.onBackPressed()
            }
            .setNegativeButton(R.string.try_again) { _, _ ->
                viewModel.getRooms()
            }
            .create()

        errorDialog.show()
    }
}