package com.example.labthree.ui

import com.example.labthree.data.model.CatalogDTO
import com.example.labthree.data.model.ImageDTO

interface MainContract {
    interface View {
        fun displayImage(image: ImageDTO)
        fun displayCatalog(catalog: CatalogDTO)

        fun displayError()
    }

    interface Presenter {
        fun image()
        fun catalog()
    }
}