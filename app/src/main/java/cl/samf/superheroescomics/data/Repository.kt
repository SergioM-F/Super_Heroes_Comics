package cl.samf.superheroescomics.data

import android.util.Log
import androidx.lifecycle.LiveData
import cl.samf.superheroescomics.data.local.DetailsHeroeEntity
import cl.samf.superheroescomics.data.local.HeroeDao
import cl.samf.superheroescomics.data.local.HeroeEntity
import cl.samf.superheroescomics.data.remote.DetailsHeroe
import cl.samf.superheroescomics.data.remote.HeroeApi
import cl.samf.superheroescomics.data.remote.HeroeList


class Repository(private val heroeApi: HeroeApi, private val heroeDao: HeroeDao) {

    fun getHeroe(): LiveData<List<HeroeEntity>> = heroeDao.getHeroe()
    fun getDetailsIdHeroe(id: Int): LiveData<DetailsHeroeEntity> = heroeDao.getDetailsHeroe(id)


    suspend fun loadHeroe() {

        val response = heroeApi.getData()

        if (response.isSuccessful) {
            val resp = response.body()
            resp?.let { heroe ->
                val heroeEntity = heroe.map { it.convert() }
                heroeDao.insertHeroe(heroeEntity)
            }

        }
    }

    suspend fun detailsHeroe(id: Int) {
        try {


            val response = heroeApi.getDetailsHeroe(id)

            if (response.isSuccessful) {

                val resp = response.body()
                resp?.let { heroeDetails ->
                    val detailsHeroeEntity = heroeDetails.convertDetails()
                    heroeDao.insertDetailsHeroe(detailsHeroeEntity)
                    Log.d("ok", "${response.message()}")
                }

            } else {
                Log.d("Error", "${response.errorBody()} ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d("Error", "${e.message}")
        }
    }

}



fun DetailsHeroe.convertDetails(): DetailsHeroeEntity =
    DetailsHeroeEntity(this.id, this.nombre, this.origen, this.imagenLink, this.poder, this.Año_creacion, this.color, this.traduccion)

