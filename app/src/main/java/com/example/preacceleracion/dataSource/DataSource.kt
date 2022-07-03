package com.example.preacceleracion.dataSource

import com.example.preacceleracion.api.Api
import com.example.preacceleracion.api.Retrofit
import com.example.preacceleracion.api.informacionApi
import com.example.preacceleracion.modelo.PeliculaDetalle
import com.example.preacceleracion.modelo.PeliculaResponses
import retrofit2.Response

class DataSource (private val api: Api = Retrofit.retrofit().create(Api::class.java)) {

  suspend  fun getCharacter():Response<PeliculaResponses<List<PeliculaDetalle>>> {
        return api.getCharacter(informacionApi.API_KEY)

    }
}