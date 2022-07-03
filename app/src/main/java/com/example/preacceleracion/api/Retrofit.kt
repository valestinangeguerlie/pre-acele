package com.example.preacceleracion.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.preacceleracion.api.informacionApi

object Retrofit {
    fun retrofit():Retrofit {
        return Retrofit.Builder().baseUrl(informacionApi.API_URL).addConverterFactory(GsonConverterFactory.create()).build()

    }
}