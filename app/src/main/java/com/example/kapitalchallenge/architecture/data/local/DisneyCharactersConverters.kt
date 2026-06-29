package com.example.kapitalchallenge.architecture.data.local

import androidx.compose.ui.input.key.type
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DisneyCharactersConverters{
    @TypeConverter
    fun fromStringList(value: ArrayList<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toStringList(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}