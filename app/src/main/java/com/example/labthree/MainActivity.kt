package com.example.labthree

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
import com.example.labthree.adapter.CategoriesAdapter
import com.example.labthree.adapter.GroupsAdapter
import com.example.labthree.api.CatalogDTO
import com.example.labthree.api.ImageDTO
import com.example.labthree.api.TestApiService
import com.example.labthree.db.Catalog
import com.example.labthree.db.Image
import com.example.labthree.db.ImageDatabase
import com.example.labthree.entity.ImageCategory
import com.example.labthree.entity.ImageGroup

class MainActivity : AppCompatActivity() {
    lateinit var imageCategories: List<Catalog>
    lateinit var imageGroups: ArrayList<ImageGroup>
    lateinit var db: ImageDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        createDB()
//        testDB()
//        fillDB()
        loadData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvCategories = findViewById<View>(R.id.categoriesRecyclerView) as RecyclerView
        imageCategories = db.catalogDao().getAll()
        val adapterCategories = CategoriesAdapter(imageCategories)
        rvCategories.adapter = adapterCategories
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val rvGroups = findViewById<View>(R.id.groupsRV) as RecyclerView
        imageGroups = ImageGroup.createImageGroups(db)
        val adapterGroups = GroupsAdapter(imageGroups)
        rvGroups.adapter = adapterGroups
        rvGroups.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun loadData() {
        Log.d("API", "loadData")
        val service = TestApiService()
        service.getImages(object : TestApiService.ImageDTOCallback {
            override fun onSuccess(image: ImageDTO) {
                displayImage(image)
                val dao = db.imageDao()
                dao.insert(Image(image))
            }
            override fun onFailure() {
                displayError()
            }
        })

        service.getCatalogs(object : TestApiService.CatalogDTOCallback {
            override fun onSuccess(catalogs: List<CatalogDTO>) {
                displayCatalogs(catalogs)
                val dao = db.catalogDao()
                for (c in catalogs) {
                    dao.insert(Catalog(0, c.title, c.n))
                }
            }
            override fun onFailure() {
                displayError()
            }
        })
    }

    private fun displayImage(image: ImageDTO) {
        Log.d("API", image.title)
        Log.d("API", image.description)
        Log.d("API", "${image.year}")
        Log.d("API", "${image.month}")
        Log.d("API", "${image.day}")
        Log.d("API", "${image.size}")
        Log.d("API", image.place)
    }

    private fun displayCatalogs(catalogs: List<CatalogDTO>) {
        Log.d("API", catalogs.toString())
    }
    private fun displayError() {
        Log.d("API", "error loading data")
        Toast.makeText(MainActivity@ this, "Failed to load data", Toast.LENGTH_LONG).show()
    }

    private fun createDB() {
        db = Room.databaseBuilder(
            applicationContext,
            ImageDatabase::class.java, "image-db"
        ).allowMainThreadQueries().build()
    }

    private fun fillDB() {
        val image1 = Image(1, "Red roses", "Beautiful flowers", 2024, 4, 23, 4.53f, "Lviv")
        val image2 = Image(2, "White dandelions", "Near my home", 2024, 4, 24, 5.31f, "Lviv")
        val image3 = Image(3, "T3", "D3", 2024, 4, 1, 5.31f, "Lviv")
        val image4 = Image(4, "T4", "D4", 2024, 3, 3, 3.21f, "Lviv")
        val image5 = Image(5, "T5", "D5", 2024, 3, 3, 3.27f, "Lviv")
        val imageDAO = db.imageDao()
        imageDAO.insert(image1)
        imageDAO.insert(image2)
        imageDAO.insert(image3)
        imageDAO.insert(image4)
        imageDAO.insert(image5)
        val catalogDAO = db.catalogDao()
        val catalog1 = Catalog(1, "Favourites", 252)
        val catalog2 = Catalog(2, "Travel", 346)
        val catalog3 = Catalog(3, "Nature", 179)
        catalogDAO.insert(catalog1)
        catalogDAO.insert(catalog2)
        catalogDAO.insert(catalog3)
    }

    private fun testDB() {
        testInsert()
        testRead()
        testDelete()
        testRead()
        testUpdate()
        testRead()
    }

    private fun writeMessage(message: String) {
        Log.d("TEST", message)
    }

    private fun testInsert() {
        writeMessage("Insert image")
        val image1 = Image(1, "Red roses", "Beautiful flowers", 2024, 4, 23, 4.53f, "Lviv")
        val image2 = Image(2, "White dandelions", "Near my home", 2024, 4, 24, 5.31f, "Lviv")
        val dao = db.imageDao()
        dao.insert(image1)
        dao.insert(image2)
        val catalogDAO = db.catalogDao()
        val catalog1 = Catalog(1, "Favourites", 252)
        val catalog2 = Catalog(2, "Travel", 346)
        val catalog3 = Catalog(3, "Cats", 179)
        catalogDAO.insert(catalog1)
        catalogDAO.insert(catalog2)
        catalogDAO.insert(catalog3)
    }

    private fun testRead() {
        writeMessage("Read")
        val imageDAO = db.imageDao()
        val images = imageDAO.getAll()
        for (image in images) {
            writeMessage("$image")
        }

        val catalogDAO = db.catalogDao()
        val catalogs = catalogDAO.getAll()
        for (catalog in catalogs) {
            writeMessage("$catalog")
        }
    }

    private fun testDelete() {
        writeMessage("Delete image")
        val dao = db.imageDao()
        val image2 = Image(2, "White dandelions", "Near my home", 2024, 4, 24, 5.31f, "Lviv")
        dao.delete(image2)
    }

    private fun testUpdate() {
        writeMessage("Update image")
        val dao = db.imageDao()
        val image1 = Image(1, "White dandelions", "Near my neighbours home", 2024, 4, 24, 5.31f, "Lviv")
        dao.update(image1)
    }
}