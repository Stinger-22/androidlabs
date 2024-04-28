package com.example.labthree.data

import com.example.labthree.data.model.CatalogDTO
import com.example.labthree.data.model.ImageDTO

interface DataSource {
    fun getImages(callback: ImageDTOCallback)
    fun getCatalogs(callback: CatalogDTOCallback)

    interface ImageDTOCallback {
        fun onSuccess(image: ImageDTO)
        fun onFailure()
    }

    interface CatalogDTOCallback {
        fun onSuccess(catalogs: List<CatalogDTO>)
        fun onFailure()
    }
}