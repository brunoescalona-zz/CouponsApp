package com.example.couponsapp.data.local.converters

import androidx.room.TypeConverter
import com.example.couponsapp.data.local.entities.RelatedProductEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListConverter {

    @TypeConverter
    fun fromString(value: String?): List<RelatedProductEntity>? {
        val listType: Type = object : TypeToken<List<RelatedProductEntity>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<RelatedProductEntity>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}