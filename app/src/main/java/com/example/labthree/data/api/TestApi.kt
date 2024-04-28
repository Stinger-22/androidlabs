package com.example.labthree.data.api

import com.example.labthree.data.model.CatalogDTO
import com.example.labthree.data.model.ImageDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TestApi {
    @GET("b/661eee1dad19ca34f85b1fce?meta=false")
    fun getImages(@Header("X-Access-Key") secretKey: String): Call<ImageDTO>

    @GET("b/661fc4f9e41b4d34e4e5c42d?meta=false")
    fun getCatalogs(@Header("X-Access-Key") secretKey: String): Call<List<CatalogDTO>>
}