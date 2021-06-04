package com.example.baari.mvvmtask.retrofit.responce

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "users")
data class UserData (

	@SerializedName("id") val id : Int,
	@SerializedName("email") val email : String,
	@PrimaryKey(autoGenerate = false)
	@SerializedName("first_name") val first_name : String,
	@SerializedName("last_name") val last_name : String,
	@SerializedName("avatar") val avatar : String
)