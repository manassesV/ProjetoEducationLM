package com.manasses.manab.project.data.local.converter

import android.arch.persistence.room.TypeConverter
import java.util.*


object  DateConverter{

    @TypeConverter @JvmStatic
    fun toDates(timestamp: Long?): Date?{
        return if (timestamp == null) null else Date(timestamp)
    }


    @TypeConverter @JvmStatic
    fun toTimestamp(date: Date?):Long?{
       return date?.time
    }
}