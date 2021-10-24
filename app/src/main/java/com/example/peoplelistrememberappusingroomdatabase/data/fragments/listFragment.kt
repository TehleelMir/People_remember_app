package com.example.peoplelistrememberappusingroomdatabase.data.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peoplelistrememberappusingroomdatabase.R
import com.example.peoplelistrememberappusingroomdatabase.data.User
import com.example.peoplelistrememberappusingroomdatabase.data.UserViewModal
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.MainActivity2
import com.google.android.material.floatingactionbutton.FloatingActionButton

class listFragment() : Fragment(), ListAdapter.UpdateUserCallBack {
    private lateinit var userViewModal: UserViewModal

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModal = ViewModelProvider(this).get(UserViewModal::class.java)
        val view = inflater.inflate((R.layout.fragment_list), container, false)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab_button)
        fab.setOnClickListener {
           val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, addItemFragment("ADD_ITEM"))
            fragmentTransaction.addToBackStack("")
            fragmentTransaction.commit()
        }
        fab.setOnLongClickListener {
            startActivity(Intent(activity, MainActivity2::class.java))
            true
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ListAdapter(this)
        recyclerView.adapter = adapter

        userViewModal.readAllData.observe(viewLifecycleOwner, {
            adapter.setList(it)
        })
        return view
    }

    override fun updateUser(user: User) {
        val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, addItemFragment("UPDATE_ITEM", user))
        fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }

    override fun deleteUser(user: User) {
        userViewModal.deleteUser(user.id)
    }
} 