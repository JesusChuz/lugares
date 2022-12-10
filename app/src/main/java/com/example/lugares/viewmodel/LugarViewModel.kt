package com.example.lugares.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lugares.data.LugarDatabase
import com.example.lugares.model.Lugar
import com.example.lugares.repository.LugarRepository
/*import com.example.lugares.data.LugarDatabase
import com.example.lugares.model.Lugar
import com.example.lugares.repository.LugarRepository*/
import kotlinx.coroutines.launch

class LugarViewModel(application: Application) : AndroidViewModel(application) {
    val obtenerLugares: LiveData<List<Lugar>>
    private val repository: LugarRepository

    init {
        val lugarDao = LugarDatabase.getDatabase(application).lugarDao()
        repository = LugarRepository(lugarDao)
        obtenerLugares = repository.obtenerLugares
    }
    fun guardarLugar(lugar: Lugar){
        viewModelScope.launch { repository.guardarLugar(lugar) }
    }
    fun updateLugar(lugar: Lugar){
        viewModelScope.launch { repository.updateLugar(lugar) }
    }
    fun eliminarLugar(lugar: Lugar){
        viewModelScope.launch { repository.eliminarLugar(lugar) }
    }
}