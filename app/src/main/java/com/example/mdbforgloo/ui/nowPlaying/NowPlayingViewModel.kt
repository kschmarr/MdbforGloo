package com.example.mdbforgloo.ui.nowPlaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mdbforgloo.api.ApiServiceManager
import com.example.mdbforgloo.data.NowPlaying
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewModel : ViewModel() {

    private val _nowPlaying = MutableLiveData<NowPlaying>().apply {
        val apiService = ApiServiceManager.getApiService()
        apiService.getNowPlaying(1.toString()).enqueue(object : Callback<NowPlaying> {
            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
            }

            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                value = response.body()
            }
        })
    }
    val nowPlaying: LiveData<NowPlaying> = _nowPlaying
}