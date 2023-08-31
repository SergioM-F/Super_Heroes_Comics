package cl.samf.superheroescomics.data.remote

import com.google.gson.annotations.SerializedName

data class DetailsHeroe(
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    @SerializedName("Año_creacion") val anoCreacion: Int,
    val color: String,
    val traduccion: Boolean

    )
