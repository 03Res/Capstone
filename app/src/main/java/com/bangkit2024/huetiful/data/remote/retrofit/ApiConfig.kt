package com.bangkit2024.huetiful.data.remote.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiConfig {

    // not actual base url
    // change later
    var BASE_URL = "https://story-api.dicoding.dev/v1/"

    fun getAuthApiService() : AuthApiService {
        // @best-practice
        // change logging interceptor to only shown in debugging mode later
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // Store the base url in BuildConfig later
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(AuthApiService::class.java)
    }
}