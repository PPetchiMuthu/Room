package com.example.android.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao  {

    @Query("SELECT * FROM user_details ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Insert
    suspend fun addUser(user: User)

//    @Delete
//    suspend fun deleteUser(user: User)
//
//    @Query("DELETE FROM user_details")
//    suspend fun deleteAllUser()

    @Update
    suspend fun updateUser(user: User)

}