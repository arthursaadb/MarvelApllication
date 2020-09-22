package com.example.marvelapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.R
import com.example.marvelapplication.data.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterRecyclerViewAdapter(private val results: List<Result>) :
    RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(results[position])
    }

    override fun getItemCount(): Int = results.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(result: Result) {
            view.tv_character_name.text = result.name

            Picasso.get()
                .load(result.thumbnail.getPoster())
                .fit()
                .centerCrop()
                .into(view.iv_character_image)
        }
    }
}