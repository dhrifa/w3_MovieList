package com.example.movielisttest.model

/**
 * in memory data representation
 * toString()
 * hashCode()
 * equals()
 * copy()
 * componentN()
 * itt can not be inherited
 * ittt needs at lesst one field/property
 */
data class MovieResponse(
    val title: String,
    val image: String,
    val rating: Float,
    val releaseYear: Int,
    val genre: List<String>

)

class Response: ArrayList<MovieResponse>()//nott good practice
