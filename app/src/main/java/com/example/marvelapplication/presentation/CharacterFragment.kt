package com.example.marvelapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.Result
import com.example.marvelapplication.presentation.adapter.CharacterRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_character.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CharacterFragment : Fragment(), CharactersContract.View {
    val presenter: CharactersContract.Presenter by inject { parametersOf(this) }

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

    companion object {
        fun newInstance() =
            CharacterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}