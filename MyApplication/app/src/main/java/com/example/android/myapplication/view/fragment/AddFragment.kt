package com.example.android.myapplication.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.myapplication.R
import com.example.android.myapplication.database.User
import com.example.android.myapplication.databinding.FragmentAddBinding
import com.example.android.myapplication.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.button.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val age = binding.age.text

        if (isValid(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            viewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please Give All Data", Toast.LENGTH_LONG).show()

        }
    }

    private fun isValid(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}