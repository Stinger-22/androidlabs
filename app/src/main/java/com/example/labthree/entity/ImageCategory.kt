package com.example.labthree.entity

class ImageCategory(
    val name: String,
    val numberOfPhotos: Int) {

    companion object {
        fun createImageCategories(): List<ImageCategory> {
            val categories = ArrayList<ImageCategory>()
            categories.add(ImageCategory("Favourites", 252))
            categories.add(ImageCategory("Travel", 346))
            categories.add(ImageCategory("Cats", 179))
            return categories
        }
    }
}