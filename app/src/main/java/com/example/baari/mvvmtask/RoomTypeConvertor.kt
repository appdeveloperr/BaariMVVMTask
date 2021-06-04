package com.example.baari.mvvmtask

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.example.baari.mvvmtask.retrofit.responce.Source


class RoomTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun stringToSource(str: String): Source {
        return Gson().fromJson(str, Source::class.java)
    }

}