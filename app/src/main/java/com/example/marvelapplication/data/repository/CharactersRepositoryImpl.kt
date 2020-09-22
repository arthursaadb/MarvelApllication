package com.example.marvelapplication.data.repository

import com.example.marvelapplication.data.network.ApiService

class CharactersRepositoryImpl(private val apiService: ApiService) : CharactersRepository {
    override fun getCharacters() = apiService.getCharacters()
}