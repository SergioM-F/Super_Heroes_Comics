package cl.samf.superheroescomics.data

import cl.samf.superheroescomics.data.local.HeroeEntity
import cl.samf.superheroescomics.data.remote.HeroeList

fun HeroeList.convert(): HeroeEntity =
    HeroeEntity(this.id, this.nombre, this.origen, this.imagenLink, this.poder, this.anio_creacion)