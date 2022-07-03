package com.example.preacceleracion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.preacceleracion.adapter.PeliculaViewModelo
import com.example.preacceleracion.databinding.ActivityMainBinding
import android.util.Log
import android.util.Log.d
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preacceleracion.adapter.PeliculaAdapter
import com.example.preacceleracion.api.Api
import com.example.preacceleracion.modelo.MyData
import com.example.preacceleracion.modelo.PeliculaDetalle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.*

import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val api_key = "5c2d9a619b75e6c3df163151d569da26"
    val Base_url = "https://developers.themoviedb.org/3/"
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PeliculaViewModelo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        val items: List<PeliculaDetalle> = listOf()
        camino()
        evento()
        peliculaAdapter = PeliculaAdapter(baseContext, items)
        recyclerView = findViewById(R.id.recyclerView)


        recyclerView.adapter = PeliculaAdapter

        mostPopular()
    }

    private fun camino() {
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    private fun mostPopular() {

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


        val retrofitData = retrofitBuilder.getMostPopular(api_key, languague)
        retrofitData.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>?) {
                if (response?.isSuccessful!!) {
                    val responseBody = response?.body()!!
                    peliculaAdapter = PeliculaAdapter(baseContext, responseBody.results)
                    peliculaAdapter.notifyDataSetChanged()
                    recyclerView.adapter = PeliculaAdapter
                }else{
                    Toast.makeText(applicationContext, "response en failure", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MyData>?, t: Throwable) {
                Toast.makeText(getApplicationContext(), "Failure: "+ t.message, Toast.LENGTH_SHORT).show()
                d("Main Activity", "onFailure: " + t.message)
            }

    }else{
        Toast.makeText(applicationContext, "response en failure", Toast.LENGTH_LONG).show()
    }
}

override fun onFailure(call: Call<MyData>?, t: Throwable) {
    Toast.makeText(getApplicationContext(), "Failure: "+ t.message, Toast.LENGTH_SHORT).show()
    d("Main Activity", "onFailure: " + t.message)
}
}






}