package com.example.mdbforgloo.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mdbforgloo.api.ApiServiceManager
import com.example.mdbforgloo.data.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> = _movieId

    val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    fun setMovieId(id: Int) {
        _movieId.value = id
        getMovie()
    }

    private fun getMovie(){
        val apiService = ApiServiceManager.getApiService()
        _movieId.value?.let{id ->
            apiService.getMovie(id.toString()).enqueue(object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                }
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    _movie.value = response.body()
                }
            })
        }
    }
}