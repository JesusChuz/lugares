package com.example.lugares.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lugares.data.LugarDao
import com.example.lugares.model.Lugar
import com.example.lugares.repository.LugarRepository
import kotlinx.coroutines.launch

class LugarViewModel(application: Application) : AndroidViewModel(application) {
    val obtenerLugares: MutableLiveData<List<Lugar>>
    private val repository: LugarRepository = LugarRepository(LugarDao())

    init {
        obtenerLugares = repository.getLugares
    }
    fun guardarLugar(lugar: Lugar){
        viewModelScope.launch { repository.agregarLugar(lugar) }
    }
    fun eliminarLugar(lugar: Lugar){
        viewModelScope.launch { repository.eliminarLugar(lugar) }
    }
}