package com.example.lugares.ui.lugar

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lugares.R
import com.example.lugares.databinding.FragmentAddLugarBinding
import com.example.lugares.model.Lugar
import com.example.lugares.viewmodel.LugarViewModel
import com.example.programovil.utilidades.AudioUtiles
import com.example.programovil.utilidades.ImagenUtiles
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class AddLugarFragment : Fragment() {
    private var _binding: FragmentAddLugarBinding? = null
    private val binding get() = _binding!!

    private lateinit var lugarViewModel: LugarViewModel
    private lateinit var audioUtiles: AudioUtiles
    private lateinit var imagenUtiles: ImagenUtiles
    private lateinit var tomarFotoActivity: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)
        lugarViewModel = ViewModelProvider(this).get(LugarViewModel::class.java)
        binding.btAgregar.setOnClickListener {
            binding.progressBar.visibility = ProgressBar.VISIBLE
            binding.msgMensaje.text = getString(R.string.msgSubiendo)
            binding.msgMensaje.visibility = TextView.VISIBLE
            subirAudio()
        }
        // Inflate the layout for this fragment
        //Audio
        audioUtiles = AudioUtiles(requireActivity(), requireContext(), binding.btAccion, binding.btPlay, binding.btDelete,
                getString(R.string.msgInicioNotaAudio), getString(R.string.msgDetieneNotaAudio))

        //Fotos
        tomarFotoActivity = registerForActivityResult(
            ActivityResultContracts.StarActivitForResult()
        ){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                imagenUtiles.actualizaFoto()
            }
        }
        imagenUtiles = ImagenUtiles(requireContext(), binding.btPhoto, binding.btRotaL, binding.btRotaR, binding.imagen, tomarFotoActivity)

        return binding.root
    }
    private fun subirAudio(){
        val audioFile = audioUtiles.audioFile
        if(audioFile.exists() && audioFile.isFile && audioFile.canRead()){
            val ruta = Uri.fromFile(audioFile)
            val rutaNube = "luagresV/${Firebase.auth.currentUser?.email}/audios/${audioFile.name}"
            val referencia :StorageReference = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta)
                .addOnSuccessListener {
                    referencia.downloadUrl
                        .addOnSuccessListener {
                             val rutaAudio = it.toString()
                            subirImagen(rutaAudio)
                        }
                }
                .addOnCanceledListener { subirImagen("") }
        }
        else{
                subirImagen("")
            }
        }
    private fun subirImagen(rutaAudio:String){
        val imagenFile = ImagenUtiles.imagenFile
        if(imagenFile.exists() && imagenFile && imagenFile.canRead()){
            val ruta = Uri.fromFile(imagenFile)
            val rutaNube = "luagresV/${Firebase.auth.currentUser?.email}"
            val referencia :StorageReference  = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta)
                .addOnSuccessListener {
                    referencia.downloadUrl
                        .addOnSuccessListener {
                            val rutaImagen= it.toString()
                            agregarLugar(rutaAudio, rutaImagen)
                        }
                }.addOnCanceledListener {
                    agregarLugar(rutaAudio,"")
                }
                else{
                   agregarLugar(rutaAudio, "")
                }
        }
    }

    private fun agregarLugar(rutaAudio:String, rutaImagen:String){
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()

        if(nombre.isNotEmpty()) {
            val lugar = Lugar("", nombre, correo, telefono, web,rutaAudio,rutaImagen)
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
