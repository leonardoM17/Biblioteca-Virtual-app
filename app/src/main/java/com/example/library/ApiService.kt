package com.example.library

import android.content.ClipData
import com.example.library.data.Libro
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("consultapalabra")
    fun getItems(
        @Query("catalogo") catalogo: Int,
        @Query("tipo") tipo: String,
        @Query("palabra") palabra: String
    ): Call<List<Libro>>
}
