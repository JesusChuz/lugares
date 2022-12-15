package com.example.lugares.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Lugar(
    var id: String,
    val nombre: String,
    val correo: String?,
    val telefono: String?,
    val web: String?, //? = permite el manejo de nulos
    val rutAudio: String?,
    val rutaImagen: String?
) : Parcelable {
    constructor():
            this("", "", "", "", "", "")
}