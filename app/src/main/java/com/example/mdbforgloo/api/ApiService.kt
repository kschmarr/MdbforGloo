package com.example.mdbforgloo.api

import com.example.mdbforgloo.data.Movie
import com.example.mdbforgloo.data.NowPlaying
import com.example.mdbforgloo.data.TopRated
import com.example.mdbforgloo.data.Upcoming
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("top_rated")
    fun getTopRated(@Query("page") page: String): Call<TopRated>

    @GET("now_playing")
    fun getNowPlaying(@Query("page") page: String): Call<NowPlaying>

    @GET("upcoming")
    fun getUpcoming(@Query("page") page: String): Call<Upcoming>

    @GET("{movieId}")
    fun getMovie(@Path("movieId") movieId: String): Call<Movie>

}