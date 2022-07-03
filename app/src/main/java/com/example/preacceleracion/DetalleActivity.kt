package com.example.preacceleracion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import com.example.preacceleracion.adapter.PeliculaAdapter
import com.example.preacceleracion.api.Api
import com.example.preacceleracion.databinding.ActivityDetalleBinding
import com.example.preacceleracion.modelo.MovieCredits
import com.example.preacceleracion.modelo.PeliculaDetalle
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import com.example.preacceleracion.api.Retrofit
import com.example.preacceleracion.databinding.ActivityMainBinding




class DetalleActivity : AppCompatActivity() {
    val Base_url = "https://api.themoviedb.org/3/"
    val api_key = "5c2d9a619b75e6c3df163151d569da26"
    lateinit var peliculaAdapter: PeliculaAdapter
    private lateinit var movie_id: String
    private lateinit var titulo: String
    private lateinit var binding: ActivityDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        movie_id = intent.getStringExtra("movie_id").toString()
        titulo = intent.getStringExtra("title").toString()

    }

    private fun getMovieDetail() {

        var logg = HttpLoggingInterceptor()
        logg.setLevel(HttpLoggingInterceptor.Level.BODY)
        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logg)
            .build()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_url)
            .client(okHttpClient)
            .build()
            .create(Api::class.java)

        val retrofitData = retrofitBuilder.getMovieDetail(
            movie_id,
            api_key,
            "videos",
            "es-MX"
        )
        retrofitData.enqueue(object : Callback<PeliculaDetalle> {
            override fun onResponse(call: Call<PeliculaDetalle>, response: Response<PeliculaDetalle>?) {
                if (response?.isSuccessful!!) {
                    val responseBody = response?.body()!!
                    responseBody.homepage
                }else{
                    val imageBasePath = "https://image.tmdb.org/t/p/w300/"
                    binding.imageView.isGone = false
                    Picasso.get().load(imageBasePath + responseBody.backdrop_path).into(binding.imageView)
                }
                var genero = StringBuilder()
                for(g in responseBody.genres){
                    genero.append(g.name+", ")
                }
                binding.textViewGenero.text = "Genero: "+ genero
                binding.textViewPopularidad.text = responseBody.title
                binding.textViewFecha.text = "Fecha de estreno: "+responseBody.release_date
                binding.idiomatextView.text = "Idioma Original: "+responseBody.original_language
                val retrofitDataCredits = retrofitBuilder.getMovieCredit(movie_id, api_key)
            retrofitDataCredits.enqueue(object:Callback<MovieCredits> {
                override fun onResponse(
                    call: Call<MovieCredits>,
                    response: Response<MovieCredits>
                ) {
                    val responseBodyCredits = response.body()!!
                    peliculaAdapter = PeliculaAdapter(baseContext, responseBodyCredits.cast)
//                            binding.tvDetalle.text = responseBodyCredits.cas

                }
            }





            }

