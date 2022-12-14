package com.example.lugares.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lugares.R
import com.example.lugares.databinding.FragmentAddLugarBinding
import com.example.lugares.model.Lugar
import com.example.lugares.viewmodel.LugarViewModel

class AddLugarFragment : Fragment() {
    private var _binding: FragmentAddLugarBinding? = null
    private val binding get() = _binding!!

    private lateinit var lugarViewModel: LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)
        lugarViewModel = ViewModelProvider(this).get(LugarViewModel::class.java)
        binding.btAgregar.setOnClickListener { agregarLugar() }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun agregarLugar(){
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()

        if(nombre.isNotEmpty()) {
            val lugar = Lugar("", nombre, correo, telefono, web)
            lugarViewModel.guardarLugar(lugar)
            Toast.makeText(requireContext(), getText(R.string.ms_AddLugar), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addLugarFragment_to_nav_home)
        }else {
            Toast.makeText(requireContext(), getText(R.string.ms_FaltanValores), Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}