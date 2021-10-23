package com.example.peoplelistrememberappusingroomdatabase.data.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.peoplelistrememberappusingroomdatabase.R
import com.example.peoplelistrememberappusingroomdatabase.data.User
import com.example.peoplelistrememberappusingroomdatabase.data.UserViewModal

class addItemFragment(val type: String, val user: User? = null) : Fragment() {
    private lateinit var userViewModal: UserViewModal

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModal = ViewModelProvider(this).get(UserViewModal::class.java)
        val view = inflater.inflate((R.layout.fragment_add_item), container, false)
        view.findViewById<Button>(R.id.addButton).text = if(type == "ADD_ITEM") "ADD PERSON" else "UPDATE PERSON"
        if(type == "UPDATE_ITEM") {
            view.apply {
                findViewById<EditText>(R.id.age).text = Editable.Factory.getInstance().newEditable(user!!.age.toString())
                findViewById<EditText>(R.id.firstName).text = Editable.Factory.getInstance().newEditable(user!!.firstName.toString())
                findViewById<EditText>(R.id.lastName).text = Editable.Factory.getInstance().newEditable(user!!.lastName.toString())
            }
        }
        view.findViewById<Button>(R.id.addButton).setOnClickListener {
            val firstName = view.findViewById<EditText>(R.id.firstName).text.toString()
            val lastName  = view.findViewById<EditText>(R.id.lastName).text.toString()
            val age       = view.findViewById<EditText>(R.id.age).text.toString()
            if(firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()) {
                if(type == "ADD_ITEM") {
                    userViewModal.addUser(
                        User(
                            0,
                            firstName,
                            lastName,
                            age.toInt()
                        )
                    ) // even we are passing 0 as an id here, but since we have set id as a primary key in its modal class room will ignore this 0 and will add the correct id
                    Toast.makeText(requireContext(), "Person Added", Toast.LENGTH_SHORT).show()
                } else {
                    userViewModal.updateUser(User(user!!.id, firstName, lastName, age.toInt()))
                    Toast.makeText(requireContext(), "User Updated", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(requireContext(), "Please Enter all the fields first", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}