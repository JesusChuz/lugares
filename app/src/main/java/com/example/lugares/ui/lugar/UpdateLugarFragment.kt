package com.example.lugares.ui.lugar

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.lugares.R
import androidx.navigation.fragment.findNavController
import com.example.lugares.adapter.LugarAdapter
import com.example.lugares.databinding.FragmentUpdateLugarBinding
import com.example.lugares.model.Lugar
import com.example.lugares.viewmodel.LugarViewModel


class UpdateLugarFragment : Fragment() {
    //recuperar los elementos enviados
    private val args by navArgs<UpdateLugarFragmentArgs>()

    private var _binding: FragmentUpdateLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel: LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lugarViewModel = ViewModelProvider(this).get(LugarViewModel::class.java)
        _binding = FragmentUpdateLugarBinding.inflate(inflater, container, false)

        // carga de datos de lugar
        binding.etNombre.setText(args.lugarArg.nombre)
        binding.etCorreo.setText(args.lugarArg.correo)
        binding.etTelefono.setText(args.lugarArg.telefono)
        binding.etWeb.setText(args.lugarArg.web)
        binding.btUpdateLugar.setOnClickListener { updateLugar() }
        binding.btDeleteLugar.setOnClickListener { deleteLugar() }

        // setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //si es el borrado
        if(item.itemId==R.id.menu_delete){
            deleteLugar()
        }
        return super.onOptionsItemSelected(item)
    }*/


    private fun updateLugar() {
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if (nombre.isNotEmpty()) {
            val lugar = Lugar(args.lugarArg.id,nombre, correo, telefono, web)
            lugarViewModel.guardarLugar(lugar)
            Toast.makeText(requireContext(), getText(R.string.ms_UpdateLugar), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateLugarFragment_to_nav_lugar)
        } else {
            Toast.makeText(requireContext(), getText(R.string.ms_FaltanValores), Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteLugar(){
        /*val build=AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)) {_,_ ->
            lugarViewModel.eliminarLugar(args.lugar)
            Toast.makeText(
                requireContext(),
                getString(R.string.deleted)+" ${args.lugar.nombre}!",
                Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addLugarFragment_to_nav_lugar)
        }
        builder.setNegativeButton(getString(R.string.no)) {_,_ ->}
        builder.setTitle(R.string.deleted)
        builder.setMessage(getString(R.string.seguroBorrar)+" ${args.lugar.nombre}?")
        builder.create().show()*/
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if (nombre.isNotEmpty()) {
            val lugar = Lugar(args.lugarArg.id,nombre, correo, telefono, web)
            lugarViewModel.eliminarLugar(lugar)
            Toast.makeText(requireContext(), getText(R.string.ms_DeleteLugar), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateLugarFragment_to_nav_lugar)
        } else {
            Toast.makeText(requireContext(), getText(R.string.ms_FaltanValores), Toast.LENGTH_LONG).show()
        }
    }

}