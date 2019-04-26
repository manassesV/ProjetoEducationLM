package com.manasses.manab.project.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.manasses.manab.project.data.local.converter.DateConverter
import com.manasses.manab.project.data.local.dao.UserDao
import com.manasses.manab.project.data.local.entity.User

@Database(entities = [User::class], version = 1)
@TypeConverters(DateConverter::class)
abstract  class MyDatabase: RoomDatabase(){

    abstract fun UserDao(): UserDao

    companion object {
        private val INSTANCE:MyDatabase? = null
    }

}