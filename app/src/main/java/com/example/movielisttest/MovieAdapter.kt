package com.example.movielisttest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielisttest.model.MovieResponse
import com.squareup.picasso3.Picasso

class MovieAdapter(private val dataSet: List<MovieResponse>) ://<String>)://
    RecyclerView.Adapter<MovieViewHolder>() { //this adapter works only with this view

    /**
     * used to create tth view holder
     * tthis will be called once
     * if the sttructture of tthe view holder chsnged tthis will be caled again
     * get @LayoutInflater.from(context) contextt needs tto be FROM base context (contextTehmeswrapper)
     * basecontext provides themes resources and the infalter
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,//is the recycler, where tthe item will be inflated, also is base context
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.movie_item_layout, parent,
                    false
                )//when inflate the view:false nottt now, the 1 option (when true) recycler is created on runtime
        )
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        // if (position % 2 == 0) holder.itemView.setBackgroundColor(Color.parseColor("#fde9ea"))
        holder.tvMovieTitle.text = dataSet[position].title//dataSet[position]
        holder.rbMovieRating.rating = dataSet[position].rating
        holder.tvMovieGenre.text = dataSet[position].genre.toString()
        holder.tvMovieYear.text = dataSet[position].releaseYear.toString()

        holder.idExpandDetail.setOnClickListener {
            //holder.idExpandDetail.setBackgroundColor(Color.BLACK)
            var b: Boolean = (holder.movieGroup.visibility == View.VISIBLE)
            holder.movieGroup.visibility = if (b) View.GONE else View.VISIBLE

//            if (holder.movieGroup.visibility == View.GONE)
//                holder.movieGroup.visibility = View.VISIBLE
//            else
//                holder.movieGroup.visibility = View.GONE
        }
        Picasso
            .Builder(holder.itemView.context)
            //.addEventListener()
            .build()
            .load(dataSet[position].image)
            //.resize()
            .into(holder.ivMovieImage)
    }

    override fun getItemCount(): Int { //return size of tthe list
        return dataSet.size
    }
}