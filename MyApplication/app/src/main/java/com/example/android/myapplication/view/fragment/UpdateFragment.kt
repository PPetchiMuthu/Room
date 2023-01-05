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
import androidx.navigation.fragment.navArgs
import com.example.android.myapplication.R
import com.example.android.myapplication.database.User
import com.example.android.myapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_first_name.setText(args.currentItem.firstName)
        view.update_last_name.setText(args.currentItem.LastName)
        view.update_age.setText(args.currentItem.id.toString())

        view.update_button.setOnClickListener {
            updateItem()
        }
//        activity?.addMenuProvider(this,viewLifecycleOwner)
        return view
    }

    private fun updateItem() {
        val firstName = update_first_name.text.toString()
        val lastName = update_last_name.text.toString()
        val age = Integer.parseInt((update_age.text).toString())

        if (isValid(firstName, lastName, update_age.text)) {
            val updateUser = User(args.currentItem.id, firstName, lastName, age)
            userViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Update Data Successfully", Toast.LENGTH_SHORT)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill the data", Toast.LENGTH_SHORT)
        }
    }

    private fun isValid(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

//    private fun deleteUser() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setPositiveButton("Yes"){_,_->
//            userViewModel.deleteUser(args.currentItem)
//            Toast.makeText(requireContext(),"Successfully Deleted",Toast.LENGTH_LONG)
//            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
//        }
//        builder.setNegativeButton("No"){_,_->}
//        builder.setTitle("Delete ${args.currentItem.firstName}?")
//        builder.setMessage("Are you sure you want to delete ${args.currentItem.firstName}")
//        builder.create().show()
//    }
//
//    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//        menuInflater.inflate(R.menu.delete_menu,menu)
//    }
//
//    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//        if(menuItem.itemId==R.menu.delete_menu){
//            deleteUser()
//            return true
//        }
//        return false
//    }
}