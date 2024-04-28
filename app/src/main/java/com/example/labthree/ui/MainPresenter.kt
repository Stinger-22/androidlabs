package com.example.labthree.ui

import android.util.Log
import androidx.room.Room
import com.example.labthree.data.DataSource
import com.example.labthree.data.model.CatalogDTO
import com.example.labthree.data.model.ImageDTO
import com.example.labthree.db.ImageDatabase
import com.example.labthree.di.DiHelper

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {
    val service: DataSource = DiHelper.getService()
    lateinit var db: ImageDatabase

    init {

    }

    override fun image() {
        Log.d("API", "Load image")
        service.getImages(object : DataSource.ImageDTOCallback {
            override fun onSuccess(image: ImageDTO) {
                view.displayImage(image)
            }

            override fun onFailure() {
                view.displayError()
            }
        })
    }

    override fun catalog() {
        Log.d("API", "Load catalogs")
        service.getCatalogs(object : DataSource.CatalogDTOCallback {
            override fun onSuccess(catalogs: List<CatalogDTO>) {
                for (catalog in catalogs) {
                    view.displayCatalog(catalog)
                }
            }

            override fun onFailure() {
                view.displayError()
            }
        })
    }
}