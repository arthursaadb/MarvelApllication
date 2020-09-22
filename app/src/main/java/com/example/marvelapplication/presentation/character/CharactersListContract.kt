package com.example.marvelapplication.presentation.character

import com.example.marvelapplication.data.model.Result

interface CharactersListContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showCharacters(results: List<Result>)
        fun showEmptyCharacters()
    }

    interface Presenter {
        fun getCharacters()
        fun detachView()
    }
}