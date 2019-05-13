package com.manasses.manab.project.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.manasses.manab.project.data.local.entity.Pontos


@Dao
interface QuizDao {

    @Insert(onConflict = REPLACE)
    fun save(pontos: Pontos)

    @Query("SELECT * FROM Pontos")
    fun load(): Pontos
}