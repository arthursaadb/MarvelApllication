package com.example.marvelapplication.presentation.character.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterRecyclerViewAdapter(
    private val results: List<Result>,
    private val listener: (view: View, result: Result) -> Unit
) : RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(results[position], listener)
    }

    override fun getItemCount(): Int = results.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(result: Result, listener: (view: View, result: Result) -> Unit) {
            setCharacterName(result.name)
            setCharacterDescription(result)
            setCharacterImage(result.thumbnail.getPoster())
            setupClickOnImage(view.iv_character_image, listener, result)
        }

        private fun setupClickOnImage(
            ivCharacterImage: ImageView,
            listener: (view: View, result: Result) -> Unit,
            result: Result
        ) {
            with(ivCharacterImage) {
                setOnClickListener {
                    listener.invoke(it, result)
                }

                transitionName = result.id
            }
        }

        private fun setCharacterImage(image: String?) {
            Picasso.get()
                .load(image)
                .fit()
                .centerCrop()
                .into(view.iv_character_image)
        }

        private fun setCharacterDescription(result: Result) {
            view.tv_character_description.text = result.description
        }

        private fun setCharacterName(name: String) {
            view.tv_character_name.text = name
        }
    }
}