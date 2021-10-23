package com.example.peoplelistrememberappusingroomdatabase.data.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.peoplelistrememberappusingroomdatabase.R
import com.example.peoplelistrememberappusingroomdatabase.data.User

class ListAdapter(val updateUserCallBack: UpdateUserCallBack): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var list = emptyList<User>()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val age = view.findViewById<TextView>(R.id.age)
        val name = view.findViewById<TextView>(R.id.name)
        val id = view.findViewById<TextView>(R.id.id)
        val update = view.findViewById<ImageView>(R.id.updateBtn)
        val delete = view.findViewById<ImageView>(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_for_recyclerview, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = list[position]
        holder.age.text = user.age.toString()
        holder.name.text = "${user.firstName} ${user.lastName}"
        holder.id.text = user.id.toString()
        holder.update.setOnClickListener {
            updateUserCallBack.updateUser(user)
        }
        holder.delete.setOnClickListener {
         updateUserCallBack.deleteUser(user)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<User>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface UpdateUserCallBack {
        fun updateUser(user: User)
        fun deleteUser(user: User)
    }
}