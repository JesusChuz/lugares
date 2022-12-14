package com.example.lugares.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lugares.data.LugarDao
import com.example.lugares.model.Lugar

class LugarRepository(private val lugarDao: LugarDao){
    fun agregarLugar(lugar:Lugar){
        lugarDao.addLugar(lugar)
    }

    fun eliminarLugar(lugar: Lugar){
        lugarDao.eliminarLugar(lugar)
    }

    val getLugares: MutableLiveData<List<Lugar>> = lugarDao.getLugares()

}