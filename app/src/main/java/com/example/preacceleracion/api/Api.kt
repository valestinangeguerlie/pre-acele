package com.example.preacceleracion.api

import android.telecom.Call
import com.example.preacceleracion.modelo.PeliculaDetalle
import com.example.preacceleracion.modelo.PeliculaResponses
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response



interface Api {
    @GET("movie/popular?")
    suspend fun getCharacter (
        @Query("api_key")  api_key: String ): Response<PeliculaResponses<List<PeliculaDetalle>>>

    }
