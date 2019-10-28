package com.example.mdbforgloo.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mdbforgloo.api.ApiServiceManager
import com.example.mdbforgloo.data.Upcoming
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingViewModel : ViewModel() {

    private val _upcoming = MutableLiveData<Upcoming>().apply {
        val apiService = ApiServiceManager.getApiService()
        apiService.getUpcoming(1.toString()).enqueue(object : Callback<Upcoming> {
            override fun onFailure(call: Call<Upcoming>, t: Throwable) {
            }

            override fun onResponse(call: Call<Upcoming>, response: Response<Upcoming>) {
                value = response.body()
            }
        })
    }
    val upcoming: LiveData<Upcoming> = _upcoming
}