package com.example.peoplelistrememberappusingroomdatabase.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModal(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { // here im using viewModalScope which is a part of kotlin corotines and Dispatchers.IO
            //means that i want to run this code in the backgroudn thread.
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(id)
        }
    }
}