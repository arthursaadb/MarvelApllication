package com.example.marvelapplication.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.Result
import com.example.marvelapplication.presentation.character.adapter.CharacterRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_character.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CharacterListFragment : Fragment(),
    CharactersListContract.View {
    val presenter: CharactersListContract.Presenter by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getCharacters()
    }

    override fun showLoading() {
        loading.animate()
    }

    override fun hideLoading() {
        loading.clearAnimation()
    }

    override fun showCharacters(results: List<Result>) {
        recyclerView.adapter = CharacterRecyclerViewAdapter(results)
    }

    override fun showEmptyCharacters() {

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}