package com.example.roomavaliabilityapp.presentation.people

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomavaliabilityapp.R
import com.example.roomavaliabilityapp.databinding.FragmentPeopleBinding
import com.example.roomavaliabilityapp.presentation.adaptor.PeopleAdaptor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {
    private lateinit var binding: FragmentPeopleBinding
    private val viewModel: PeopleViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        with(viewModel) {
            viewModelState.observe(viewLifecycleOwner, ::viewStateChange)
            getPeople()
        }
        return binding.root
    }

    fun viewStateChange(peopleViewModelState: PeopleViewmodel.State) {
        when (peopleViewModelState) {
            is PeopleViewmodel.State.FullScreenLoading -> {
                binding.loadingSpinner.visibility = View.VISIBLE
            }
            is PeopleViewmodel.State.FullScreenError -> {
                binding.loadingSpinner.visibility = View.GONE
                displayErrorDialog()
            }
            is PeopleViewmodel.State.Content -> {
                binding.peopleList.adapter = PeopleAdaptor(peopleViewModelState.people)
                binding.peopleList.layoutManager = LinearLayoutManager(context)
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
                viewModel.getPeople()
            }
            .create()

        errorDialog.show()
    }
}