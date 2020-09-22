package com.example.marvelapplication.di

import com.example.marvelapplication.presentation.CharactersContract
import com.example.marvelapplication.presentation.CharactersPresenter
import org.koin.dsl.module

object PresenterModule {
    val instance = module {
        factory<CharactersContract.Presenter> { (view: CharactersContract.View) ->
            CharactersPresenter(
                view,
                get()
            )
        }
    }
}