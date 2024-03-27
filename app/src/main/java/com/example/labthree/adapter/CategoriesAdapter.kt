package com.example.labthree.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labthree.R
import com.example.labthree.entity.ImageCategory

class CategoriesAdapter(private val categories: List<ImageCategory>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView = itemView.findViewById<TextView>(R.id.categoryName)
        val numberPhotosTextView = itemView.findViewById<TextView>(R.id.categoryNPhotos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoryView = inflater.inflate(R.layout.image_categories, parent, false)
        return ViewHolder(categoryView)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageCategory: ImageCategory = categories.get(position)
        val categoryTextView = holder.categoryTextView
        categoryTextView.setText(imageCategory.name)
        val numberPhotosTextView = holder.numberPhotosTextView
        numberPhotosTextView.setText("" + imageCategory.numberOfPhotos + " photos")
    }

}