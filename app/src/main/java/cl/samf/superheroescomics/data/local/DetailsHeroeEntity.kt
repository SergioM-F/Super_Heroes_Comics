package cl.samf.superheroescomics.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tabledetails")
data class DetailsHeroeEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val Año_creacion: Int,
    val color: String,
    val traduccion: Boolean
)
