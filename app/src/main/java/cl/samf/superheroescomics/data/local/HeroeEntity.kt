package cl.samf.superheroescomics.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tableheroe")
data class HeroeEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val anio_creacion : Int
)
