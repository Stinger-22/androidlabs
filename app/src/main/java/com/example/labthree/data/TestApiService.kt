package com.example.labthree.data

import com.example.labthree.data.model.CatalogDTO
import com.example.labthree.data.model.ImageDTO
import com.example.labthree.data.api.TestApi
import com.example.labthree.data.api.RetrofitApiHelper
import com.example.labthree.di.DiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestApiService : DataSource {
    var api: TestApi
    val SECRET_KEY = "\$2a\$10\$SlJTzP0aF7Pfl25V5K2Mx.xYtoSOlFscXZ1WwxFXdixIhfESrbQJu"

    init {
        api = DiHelper.getRetrofitHelper().retrofit!!.create(TestApi::class.java)
    }

    override fun getImages(callback: DataSource.ImageDTOCallback) {
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

    override fun getCatalogs(callback: DataSource.CatalogDTOCallback) {
        api.getCatalogs(SECRET_KEY).enqueue(object : Callback<List<CatalogDTO>> {
            override fun onResponse(call: Call<List<CatalogDTO>>, response: Response<List<CatalogDTO>>) {
                if (response.code() == 200 && response.body() != null)
                    callback.onSuccess(response.body()!!)
                else
                    callback.onFailure()
            }
            override fun onFailure(call: Call<List<CatalogDTO>>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}