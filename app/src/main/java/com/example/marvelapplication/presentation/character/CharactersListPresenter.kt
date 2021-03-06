package com.example.marvelapplication.presentation.character

import com.example.marvelapplication.data.model.Characters
import com.example.marvelapplication.data.repository.CharactersRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CharactersListPresenter(
    var view: CharactersListContract.View?,
    var repository: CharactersRepository
) : CharactersListContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun getCharacters() {
        compositeDisposable.add(
            repository.getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showLoading() }
                .doOnTerminate { view?.hideLoading() }
                .subscribe(this::handleSuccessGetCharacters, this::handleErrorGetCharacter)
        )
    }

    private fun handleSuccessGetCharacters(response: Characters) {
        view?.showCharacters(response.data.results)
    }

    private fun handleErrorGetCharacter(throwable: Throwable) {

    }

    override fun detachView() {
        compositeDisposable.dispose()
        view = null
    }
}