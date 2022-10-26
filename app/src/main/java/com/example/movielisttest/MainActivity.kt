package com.example.movielisttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movielisttest.model.MovieResponse
import com.example.movielisttest.model.remote.MovieNetwork

private const val TAG = "MainActivity"

/**
 * 1. create a item_layout (layoutt xml file)
 *  2.create viewHolder: subclass of recyclerview.viewholder
 * 3.create adapter: subclass of recyclerView.adapter
 * 4. configure the movielistt view
 *      4.a setadapter
 *      4.b setlayouttmanager
 *
 */

class MainActivity : AppCompatActivity() {

    private lateinit var rvMovieList: RecyclerView
    private val movieHandler =
        //recive the result of the task and send back to the looper with the runnable thread
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when(msg.what){
                    1->{
                        val listOfMovies: List<MovieResponse> =
                            msg.obj as List<MovieResponse>
                        Log.d(TAG, "handleMessage: $listOfMovies")
                        rvMovieList.adapter = MovieAdapter(listOfMovies)
                    }
                    else->{
                        msg.data?.getString("KEY")?.let {
                            Toast.makeText(
                                this@MainActivity,
                                it, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getMovieList()
    }

    private fun initViews() {
        rvMovieList = findViewById(R.id.movie_list)
//         rvMovieList.adapter = MovieAdapter( getMovieList())//generateData())//getMovieList("useList"))//
        rvMovieList.layoutManager = createLayoutManager()
    }

    private fun createLayoutManager(): RecyclerView.LayoutManager? {
        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )//start by tthe last index or tthe first

        val gridLayoutManager = GridLayoutManager(
            this, 3,
            GridLayoutManager.HORIZONTAL, false
        )

        val staggerGridLayoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
        return linearLayoutManager
    }

    private fun generateData(): List<String>{
        return (0..9).map {
            "Word $it"
        }
    }

    private fun getMovieList(){
        val network = MovieNetwork()
        var networkResult = mutableListOf<String>()

        Thread(Runnable {
            Log.d(TAG, "getMovieList: ${Thread.currentThread().name}")
            val message = Message()
            message.what = 1
            message.obj = network.getMovieList()
            movieHandler.sendMessage(message)//connecting the messaage with tthe handler

        }).start()

        Thread(Runnable {
            movieHandler.sendMessage(
                Message().apply {
                    what=2
                    data = Bundle().apply {
                        putString("KEY","${Thread.currentThread().name}")
                    }

                }
            )
        }).start()

        //  return ex.call()
    }
}
//}
