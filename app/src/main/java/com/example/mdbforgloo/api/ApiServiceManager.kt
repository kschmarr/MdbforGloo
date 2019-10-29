package com.example.mdbforgloo.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import com.example.mdbforgloo.BuildConfig
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import okhttp3.HttpUrl




object ApiServiceManager {
    val LOGTAG = ApiServiceManager.javaClass.simpleName
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private var apiService: ApiService? = null

    private fun buildMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private fun buildOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "cb2ed6c0aebdabd5ee7ed3643ff2129f")
                    .addQueryParameter("language", "en-US")
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(interceptor)
        }
        return builder.build()
    }

    fun getApiService(): ApiService {
        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(buildOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create(buildMoshi()))
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService!!
    }

    fun releaseApiService() {
        apiService = null
    }
}