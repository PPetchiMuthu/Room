package com.example.android.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.myapplication.R
import com.example.android.myapplication.database.User
import com.example.android.myapplication.view.fragment.ListFragmentDirections

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.cus_id.text = currentItem.id.toString()
        holder.itemView.cus_first_name.text = currentItem.firstName
        holder.itemView.cus_last_name.text = currentItem.LastName
        holder.itemView.cus_age.text = currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun setData(user : List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}