package com.example.labthree

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.labthree.adapter.CategoriesAdapter
import com.example.labthree.adapter.GroupsAdapter
import com.example.labthree.db.Image
import com.example.labthree.db.ImageDatabase
import com.example.labthree.entity.ImageCategory
import com.example.labthree.entity.ImageGroup
import java.time.LocalDate
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var imageCategories: ArrayList<ImageCategory>
    lateinit var imageGroups: ArrayList<ImageGroup>
    lateinit var db: ImageDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        createDB()
//        testDB()
        fillDB()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvCategories = findViewById<View>(R.id.categoriesRecyclerView) as RecyclerView
        imageCategories = ImageCategory.createImageCategories()
        val adapterCategories = CategoriesAdapter(imageCategories)
        rvCategories.adapter = adapterCategories
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val rvGroups = findViewById<View>(R.id.groupsRV) as RecyclerView
        imageGroups = ImageGroup.createImageGroups(db)
        val adapterGroups = GroupsAdapter(imageGroups)
        rvGroups.adapter = adapterGroups
        rvGroups.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
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
        val dao = db.imageDao()
        dao.insert(image1)
        dao.insert(image2)
        dao.insert(image3)
        dao.insert(image4)
        dao.insert(image5)
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
    }

    private fun testRead() {
        writeMessage("Read image")
        val dao = db.imageDao()
        val images = dao.getAll()
        for (image in images) {
            writeMessage("$image")
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