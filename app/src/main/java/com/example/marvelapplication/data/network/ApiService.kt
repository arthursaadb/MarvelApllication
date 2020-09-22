package com.example.marvelapplication.data.network

import com.example.marvelapplication.data.model.Characters
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface ApiService {
    @GET("v1/public/characters")
    fun getCharacters(): Single<Characters>
}