package com.example.preacceleracion.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.preacceleracion.modelo.PeliculaResponses
import android.R
import android.view.ViewGroup
import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class PeliculaAdapter (val context: Context, val movieList: List<PeliculaResponses>)
    :RecyclerView.Adapter<PeliculaAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var descripcion: TextView = itemView.findViewById(R.id.descripcion)
        var imagen : ImageView = itemView.findViewById(R.id.imageview2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.info_peliculas, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageBasePath = "https://image.tmdb.org/t/p/w300"
        holder.name.text = movieList[position].vote_average.toString()
        holder.descripcion.text = movieList[position].title
        Picasso.get().load(imageBasePath + movieList[position].poster_path).into(holder.imagen)

        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context, DetalleActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("movie_id", movieList[position].id.toString())
            intent.putExtra("title", movieList[position].title)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}