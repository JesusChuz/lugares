package com.example.lugares.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lugares.model.Lugar

@Dao
interface LugarDao {
    @Query("Select * from Lugar")
    fun getLugares(): LiveData<List<Lugar>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarLugar(lugar : Lugar)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateLugar(lugar: Lugar)

    @Delete
    suspend fun eliminarLugar(lugar: Lugar)
}