package com.example.movielisttest

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(private val view: View) : //holds pointts tto the entire hierarchie (movie_item_layout)
    RecyclerView.ViewHolder(view) {
    val tvMovieTitle: TextView =
        view.findViewById(R.id.tv_movie_title) //refernce to the movie view  holder
    val tvMovieGenre: TextView = view.findViewById(R.id.tv_movie_genre)
    val tvMovieYear: TextView = view.findViewById(R.id.tv_movie_year)
    val rbMovieRating: RatingBar = view.findViewById(R.id.rb_movie_rating)
    val ivMovieImage: ImageView = view.findViewById(R.id.iv_movie_image)
    val movieGroup: Group = view.findViewById(R.id.show_detail)
    val idExpandDetail: ImageButton = view.findViewById(R.id.ib_expand)
}