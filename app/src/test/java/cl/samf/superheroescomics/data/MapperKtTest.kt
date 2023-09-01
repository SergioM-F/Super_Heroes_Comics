package cl.samf.superheroescomics.data

import cl.samf.superheroescomics.data.remote.HeroeList
import org.junit.Assert.*

import org.junit.Test

class MapperKtTest {

    @Test
    fun convert() {
        //(this.id, this.nombre, this.origen, this.imagenLink, this.poder, this.anio_creacion)
        //given
        val heroe = HeroeList(1, "super", "krypton", "example.com", "volar", 1000)
        //when
        val result = heroe.convert()

        //then

        assertEquals(result.id, heroe.id)
        assertEquals(result.origen, heroe.origen)
        assertEquals(result.imagenLink, heroe.imagenLink)
        assertEquals(result.poder, heroe.poder)
        assertEquals(result.anio_creacion, heroe.anio_creacion)


    }
}