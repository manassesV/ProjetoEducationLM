package com.manasses.manab.project.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*


@Entity
data class Pontos(

    @PrimaryKey
    var id: Int = 0,

    var pontos: String = ""
)