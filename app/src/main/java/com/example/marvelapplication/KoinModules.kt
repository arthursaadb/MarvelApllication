package com.example.marvelapplication

import com.example.marvelapplication.di.NetworkModule
import com.example.marvelapplication.di.PresenterModule
import com.example.marvelapplication.di.RepositoryModule

object KoinModules {
    val modules = listOf(
        NetworkModule.instance,
        PresenterModule.instance,
        RepositoryModule.instance
    )
}