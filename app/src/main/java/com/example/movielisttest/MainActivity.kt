package com.example.movielisttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var rvMovieList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        rvMovieList = findViewById(R.id.movie_list)
        rvMovieList.adapter = MovieAdapter(generateData())
        rvMovieList.layoutManager = createLayoutManager()
    }

    private fun createLayoutManager(): RecyclerView.LayoutManager? {
        val linearLayoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,
        false)//start by tthe last index or tthe first

        val gridLayoutManager = GridLayoutManager(this, 3,
        GridLayoutManager.HORIZONTAL, false)

        val staggerGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
        return staggerGridLayoutManager
    }

    private fun generateData(): List<String> {
        return (0..9).map{
            "Movie with the name: $it"
        }
    }
}
/**
 * 1. create a item_layout (layoutt xml file)
 *  2. subclass of recyclerview.viewholder
 * 3.subclass of recyclerView.adapter
 * 4. configure the movielistt view
 *      4.a setadapter
 *      4.b setlayouttmanager
 *
 */