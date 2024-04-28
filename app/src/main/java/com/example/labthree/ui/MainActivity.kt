package com.example.labthree.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.labthree.R
import com.example.labthree.adapter.CategoriesAdapter
import com.example.labthree.adapter.GroupsAdapter
import com.example.labthree.data.DataSource
import com.example.labthree.data.model.CatalogDTO
import com.example.labthree.data.model.ImageDTO
import com.example.labthree.data.TestApiService
import com.example.labthree.db.Catalog
import com.example.labthree.db.Image
import com.example.labthree.db.ImageDatabase
import com.example.labthree.di.DiHelper
import com.example.labthree.entity.ImageGroup

class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var imageCatalogs: List<Catalog>
    lateinit var imageGroups: ArrayList<ImageGroup>
    lateinit var db: ImageDatabase
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = DiHelper.getPresenter(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        createDB()
        presenter.catalog()
        presenter.image()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvCategories = findViewById<View>(R.id.categoriesRecyclerView) as RecyclerView
        imageCatalogs = db.catalogDao().getAll()
        val adapterCategories = CategoriesAdapter(imageCatalogs)
        rvCategories.adapter = adapterCategories
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val rvGroups = findViewById<View>(R.id.groupsRV) as RecyclerView
        imageGroups = ImageGroup.createImageGroups(db)
        val adapterGroups = GroupsAdapter(imageGroups)
        rvGroups.adapter = adapterGroups
        rvGroups.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun displayImage(image: ImageDTO) {
        Log.d("API", image.title)
        Log.d("API", image.description)
        Log.d("API", "${image.year}")
        Log.d("API", "${image.month}")
        Log.d("API", "${image.day}")
        Log.d("API", "${image.size}")
        Log.d("API", image.place)
        val dao = db.imageDao()
        dao.insert(Image(image))
    }

    override fun displayCatalog(catalog: CatalogDTO) {
        Log.d("API", catalog.toString())
        val dao = db.catalogDao()
        dao.insert(Catalog(0, catalog.title, catalog.n))
    }

    override fun displayError() {
        Log.d("API", "error loading data")
        Toast.makeText(MainActivity@ this, "Failed to load data", Toast.LENGTH_LONG).show()
    }

    private fun createDB() {
        db = Room.databaseBuilder(
            applicationContext,
            ImageDatabase::class.java, "image-db"
        ).allowMainThreadQueries().build()
    }
}