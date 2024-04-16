package com.example.labthree.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestApiService {
    var api: TestApi
    val SECRET_KEY = "\$2a\$10\$SlJTzP0aF7Pfl25V5K2Mx.xYtoSOlFscXZ1WwxFXdixIhfESrbQJu"

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(TestApi::class.java)
    }

    fun getImages(callback: ImageDTOCallback) {
        api.getImages(SECRET_KEY).enqueue(object : Callback<ImageDTO> {
            override fun onResponse(call: Call<ImageDTO>, response: Response<ImageDTO>) {
                if (response.code() == 200 && response.body() != null)
                    callback.onSuccess(response.body()!!)
                else
                    callback.onFailure()
            }
            override fun onFailure(call: Call<ImageDTO>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    interface ImageDTOCallback {
        fun onSuccess(imageDTO: ImageDTO)
        fun onFailure()
    }
}