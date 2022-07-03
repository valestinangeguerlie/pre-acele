package com.example.preacceleracion.data.repositorio

import com.example.preacceleracion.dataSource.DataSource

class Repositorio (private val datos:DataSource) {
    suspend fun getCharacter() = datos.getCharacter()

}