package com.example.marvelapplication.data.repository

import com.example.marvelapplication.data.model.Characters
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {
    fun getCharacters(): Single<Characters>
}