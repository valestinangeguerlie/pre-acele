package com.example.preacceleracion.modelo

import com.google.gson.annotations.SerializedName

data class PeliculaResponses <T> (
    @SerializedName("results") val resultados: T

        )


