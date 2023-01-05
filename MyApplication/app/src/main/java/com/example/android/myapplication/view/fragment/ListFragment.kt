package com.example.android.myapplication.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.myapplication.R
import com.example.android.myapplication.adapter.UserAdapter
import com.example.android.myapplication.databinding.FragmentListBinding
import com.example.android.myapplication.viewmodel.UserViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater)

        val adapter = UserAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
//        setHasOptionsMenu(true)
        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId==R.menu.delete_menu){
//            deleteAllUser()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun deleteAllUser() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setPositiveButton("Yes"){_,_->
//            userViewModel.deleteAllUser()
//            Toast.makeText(requireContext(),"Successfully Deleted", Toast.LENGTH_LONG)
//        }
//        builder.setNegativeButton("No"){_,_->}
//        builder.setTitle("Delete everything?")
//        builder.setMessage("Are you sure you want to delete everything")
//        builder.create().show()
//    }
}