package cl.samf.superheroescomics.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroeApi {

    @GET("superheroes")
    suspend fun getData(): Response<List<HeroeList>>

    @GET("superheroes/{id}")
    suspend fun getDetailsHeroe(@Path("id") id: Int): Response<DetailsHeroe>
}