package com.example.marvelapplication.di

import com.example.marvelapplication.presentation.character.CharactersListContract
import com.example.marvelapplication.presentation.character.CharactersListPresenter
import org.koin.dsl.module

object PresenterModule {
    val instance = module {
        factory<CharactersListContract.Presenter> { (view: CharactersListContract.View) ->
            CharactersListPresenter(
                view,
                get()
            )
        }
    }
}