package com.example.movielisttest.model.remote

import android.net.Uri
import com.example.movielisttest.model.MovieResponse
import com.example.movielisttest.model.Response
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MovieNetwork {
    //    https://gist.githubusercontent.com/
//    AntoninoAN/f3fa4b2260c51a5f80904c747009289e/raw/6576691177f6b093afd3bf2bbc5e936b62d50721/MovieGist
// . ->base
    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/"
        const val ENDPOINT =
            "AntoninoAN/f3fa4b2260c51a5f80904c747009289e/raw/6576691177f6b093afd3bf2bbc5e936b62d50721/MovieGist"
        val uriPath =
            Uri.parse("$BASE_URL$ENDPOINT") //add param wittht appendparam(name param, value)
        val url = URL(uriPath.toString())
    }

    fun getMovieList(): List<MovieResponse> {
        val httpUrlConnection = url.openConnection() as HttpURLConnection

        httpUrlConnection.readTimeout = 10000
        httpUrlConnection.connectTimeout = 15000
        httpUrlConnection.requestMethod = "GET"
        httpUrlConnection.doInput = true

        httpUrlConnection.connect()

        //deserializeIS(httpUrlConnection.inputStream) is correct also
        httpUrlConnection.inputStream.run {
            deserializeIS(this)
        }.let {
            return parseToMovieResponse(it)
        }
    }

    fun deserializeIS(iS: InputStream): String {

        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(iS))
        var line: String? = reader.readLine()
        while (line != null) {
            builder.append(line)
            builder.append("\n")
            line = readLine()
        }
        if (builder.isEmpty()) {
            return "N/A"
        }
        return builder.toString()
    }

    fun parseToMovieResponse(deserialize: String): List<MovieResponse> {
        val response = JSONArray(deserialize)

        val movieResponseList = Response() //when ive my own types
        val listOfMovie = mutableListOf<MovieResponse>()

        for (index in 0 until response.length()) {//index in 0 .. response.length()-1
            val movieElement = response.getJSONObject(index)
            val movieResponse = MovieResponse(
                rating = movieElement.getDouble("rating").toFloat(),
                title = movieElement.getString("title"),
                image = movieElement.getString("image"),
                releaseYear = movieElement.getInt("releaseYear"),
                genre = movieElement.getJSONArray("genre").parseJsonArrayToList()
            )
            movieResponseList.add(movieResponse)
            listOfMovie.add(movieResponse)
        }
        return listOfMovie
    }

    private fun JSONArray.parseJsonArrayToList(): List<String> {
        val result = mutableListOf<String>()
        for (i in 0 until this.length()) {
            result.add(this.getString(i))
        }
        return result
    }
}