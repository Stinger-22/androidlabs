package com.example.labthree

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labthree.adapter.CategoriesAdapter
import com.example.labthree.adapter.GroupsAdapter
import com.example.labthree.entity.ImageCategory
import com.example.labthree.entity.ImageGroup

class MainActivity : AppCompatActivity() {
    lateinit var imageCategories: ArrayList<ImageCategory>
    lateinit var imageGroups: ArrayList<ImageGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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
        imageGroups = ImageGroup.createImageGroups()
        val adapterGroups = GroupsAdapter(imageGroups)
        rvGroups.adapter = adapterGroups
        rvGroups.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}