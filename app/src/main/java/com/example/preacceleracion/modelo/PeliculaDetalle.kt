package com.example.preacceleracion.modelo

import com.google.gson.annotations.SerializedName

data class PeliculaDetalle (

    @SerializedName("poster_path") val portada:String,
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val titulo:String,
    @SerializedName("original_language") val idioma:String,
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int,
    val homepage: String,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
)


data class Cast (
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
        )
data class MovieCredits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: Any
)
data class MyData(
    val page: Int,
    val results: List<PeliculaDetalle>,
    val total_pages: Int,
    val total_results: Int
)
