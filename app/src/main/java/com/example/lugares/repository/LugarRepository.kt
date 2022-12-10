package com.example.lugares.repository

import androidx.lifecycle.LiveData
import com.example.lugares.data.LugarDao
import com.example.lugares.model.Lugar

class LugarRepository(private val lugarDao: LugarDao){
    suspend fun guardarLugar(lugar:Lugar){
        if(lugar.id == 0){
            lugarDao.agregarLugar(lugar)
        }else{
            lugarDao.updateLugar(lugar)
        }
    }
    suspend fun updateLugar(lugar: Lugar){
        lugarDao.updateLugar(lugar)
    }
    suspend fun eliminarLugar(lugar: Lugar){
        lugarDao.eliminarLugar(lugar)
    }

    val obtenerLugares: LiveData<List<Lugar>> = lugarDao.getLugares()

}