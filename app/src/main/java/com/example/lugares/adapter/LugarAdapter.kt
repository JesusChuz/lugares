package com.example.lugares.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
//import com.example.lugares.databinding.FragmentAddLugarBinding
import com.example.lugares.databinding.LugarFilaBinding
import com.example.lugares.model.Lugar
//import com.example.lugares.ui.lugar.LugarFragmentDirections

class LugarAdapter: RecyclerView.Adapter<LugarAdapter.LugarViewHolder>(){
    //Lista de Lugares
    private var listaLugares = emptyList<Lugar>()

    fun setLugares(lugares: List<Lugar>){
        listaLugares = lugares
        notifyDataSetChanged()
    }

    inner class LugarViewHolder(private val itemBinding: LugarFilaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(lugar: Lugar){
            itemBinding.tvNombre.text = lugar.nombre
            itemBinding.tvCorreo.text = lugar.correo
            itemBinding.tvTelefono.text = lugar.telefono
            //Evento enviar Update
           /* itemBinding.vistaFila.setOnClickListener {
                val action = LugarFragmentDirections.actionNavLugarToUpdateLugarFragment(lugar)
                itemView.findNavController().navigate(action)
            }*/
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding = LugarFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LugarViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val lugar = listaLugares[position]
        holder.bind(lugar)
    }

    override fun getItemCount(): Int {
        return listaLugares.size
    }

}