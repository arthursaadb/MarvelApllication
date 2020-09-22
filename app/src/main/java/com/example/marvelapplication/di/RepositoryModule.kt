package com.example.marvelapplication.di

import com.example.marvelapplication.data.repository.CharactersRepository
import com.example.marvelapplication.data.repository.CharactersRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val instance = module {
        single<CharactersRepository> { CharactersRepositoryImpl(get()) }
    }
}