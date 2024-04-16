package com.example.labthree.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TestApi {
    @GET("b/661eee1dad19ca34f85b1fce?meta=false")
    fun getImages(@Header("X-Access-Key") secretKey: String): Call<ImageDTO>
}