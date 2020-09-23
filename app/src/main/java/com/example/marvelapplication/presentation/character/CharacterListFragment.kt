package com.example.marvelapplication.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
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
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun showCharacters(results: List<Result>) {
        recyclerView.adapter = CharacterRecyclerViewAdapter(results, this::onCharacterSelected)
    }

    override fun showEmptyCharacters() {

    }

    private fun onCharacterSelected(view: View, character: Result) {
        val sharedElement = FragmentNavigatorExtras(
            view to character.id
        )

        val action = CharacterListFragmentDirections.nextAction().setSelectedCharacter(character)

        findNavController().navigate(action, sharedElement)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}