package com.example.mdbforgloo.api

import com.example.mdbforgloo.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/top_rated?api_key=cb2ed6c0aebdabd5ee7ed3643ff2129f&language=en-US")
    fun getTopRated(@Query("page") page: String): Call<List<Movie>>

    @GET("/now_playing?api_key=cb2ed6c0aebdabd5ee7ed3643ff2129f&language=en-US")
    fun getNowPlaying(@Query("page") page: String): Call<List<Movie>>

    @GET("/upcoming?api_key=cb2ed6c0aebdabd5ee7ed3643ff2129f&language=en-US")
    fun getUpcoming(@Query("page") page: String): Call<List<Movie>>

}