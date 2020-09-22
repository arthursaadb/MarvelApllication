package com.example.marvelapplication

import com.example.marvelapplication.data.model.Characters
import com.example.marvelapplication.data.repository.CharactersRepository
import com.example.marvelapplication.presentation.character.CharactersListContract
import com.example.marvelapplication.presentation.character.CharactersListPresenter
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterPresenterTest {
    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    @RelaxedMockK
    lateinit var view: CharactersListContract.View

    @RelaxedMockK
    lateinit var repository: CharactersRepository

    @RelaxedMockK
    lateinit var characters: Characters

    lateinit var presenter: CharactersListContract.Presenter

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter =
            CharactersListPresenter(
                view,
                repository
            )
    }

    @Test
    fun `test getCharacter with success result`() {
        //Given
        every { repository.getCharacters() } returns Single.just(characters)

        //When
        presenter.getCharacters()

        //Then
        verify(exactly = 1) {
            view.apply {
                showLoading()
                hideLoading()
                showCharacters(characters.data.results)
            }
        }
    }

    @Test
    fun `test getCharacters with empty result`() {
        //Given
        every { repository.getCharacters() } returns Single.just(characters)
        every { characters.data.results } returns listOf()

        //When
        presenter.getCharacters()

        //Then
        verify(exactly = 1) {
            view.apply {
                showLoading()
                hideLoading()
                showEmptyCharacters()
            }
        }
    }

    @Test
    fun `test getCharacters with error result`() {

    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}