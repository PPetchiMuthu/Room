package com.example.android.myapplication.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_details")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var firstName: String,
    var LastName: String,
    var age: Int
) : Parcelable