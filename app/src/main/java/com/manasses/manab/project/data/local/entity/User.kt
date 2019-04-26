package com.manasses.manab.project.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity
data class User(

    @PrimaryKey
    var id: Int = 0,

    var email: String = "",

    var password: String = "",

    var name: String = "",
    var login: Boolean = false,

    var lastRefresh: Date? = null



)