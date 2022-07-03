package com.example.preacceleracion.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preacceleracion.R
import com.example.preacceleracion.api.informacionApi
import com.example.preacceleracion.databinding.InfoPeliculasBinding
import com.example.preacceleracion.modelo.PeliculaDetalle
import com.squareup.picasso.Picasso


class PeliculaViewHolder( view : View, var holder: PeliculaAdapter): RecyclerView.ViewHolder(view) {
    val binding = InfoPeliculasBinding.bind(view)


    fun bind(pelicula:PeliculaDetalle) {
        Picasso.get().load("${informacionApi.API_URL}${movie.poster_path}")
            .into(binding.imageView2)
        binding.name.text = movie.title
    }
}