package cl.samf.superheroescomics.data.remote

import com.google.gson.annotations.SerializedName

data class DetailsHeroe(
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val Año_creacion: Int,
    val color: String,
    val traduccion: Boolean

    )
