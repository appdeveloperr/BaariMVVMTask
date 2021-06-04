package com.example.baari.mvvmtask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.baari.mvvmtask.RoomTypeConvertor
import com.example.baari.mvvmtask.retrofit.responce.UserData

@Database(entities = [UserData::class, RemoteKey::class], version = 1)
@TypeConverters(RoomTypeConvertor::class)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): MyDatabase {
            return Room.databaseBuilder(context, MyDatabase::class.java, "name").build()
        }
    }

    abstract fun getDao(): Dao

}