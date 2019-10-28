package com.example.mdbforgloo.ui.topRated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mdbforgloo.api.ApiServiceManager
import com.example.mdbforgloo.data.TopRated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel : ViewModel() {

    private val _topRated = MutableLiveData<TopRated>().apply {
        val apiService = ApiServiceManager.getApiService()
        apiService.getTopRated(1.toString()).enqueue(object : Callback<TopRated> {
            override fun onFailure(call: Call<TopRated>, t: Throwable) {
            }

            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {
                value = response.body()
            }
        })
    }
    val topRated: LiveData<TopRated> = _topRated
}