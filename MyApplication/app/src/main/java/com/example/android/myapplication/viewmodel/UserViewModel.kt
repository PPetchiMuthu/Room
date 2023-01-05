package com.example.android.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.myapplication.database.User
import com.example.android.myapplication.database.UserDatabase
import com.example.android.myapplication.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<User>>
    private val userRepository : UserRepository

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        userRepository = UserRepository(userDao)
        readAllData = userRepository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }
//    fun deleteUser(user:User){
//        viewModelScope.launch(Dispatchers.IO) {
//            userRepository.deleteUser(user)
//        }
//    }
//    fun deleteAllUser(){
//        viewModelScope.launch(Dispatchers.IO) {
//            userRepository.deleteAllUser()
//        }
//    }

}