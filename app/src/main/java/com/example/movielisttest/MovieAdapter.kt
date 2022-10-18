package com.example.movielisttest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val dataSet: List<String>):
    RecyclerView.Adapter<MovieViewHolder>() { //this adapter works only with this view

    /**
     * used to create tth view holder
     * tthis will be called once
     * if the sttructture of tthe view holder chsnged tthis will be caled again
     * get @LayoutInflater.from(context) contextt needs tto be FROM base context (contextTehmeswrapper)
     * basecontext provides themes resources and the infalter
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item_layout, parent,
                    false)//when inflate the view: nottt now, the 1 option (when true) recycler is created on runtime
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder,
                                  position: Int) {
        if(position % 2 == 0) holder.itemView.setBackgroundColor(Color.parseColor("#cacaca"))
        holder.tvMovieTitle.text = dataSet[position]
    }

    override fun getItemCount(): Int { //return size of tthe list
        return dataSet.size
    }
}