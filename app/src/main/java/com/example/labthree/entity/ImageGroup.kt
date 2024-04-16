package com.example.labthree.entity

import com.example.labthree.db.Image
import com.example.labthree.db.ImageDatabase

class ImageGroup(
    val name: String,
    val images: List<Image>
    ) {

    companion object {
        fun createImageGroups(db: ImageDatabase): ArrayList<ImageGroup> {
            val groups = ArrayList<ImageGroup>()
            val dao = db.imageDao()
            groups.add(ImageGroup("This month", dao.findByMonthAndYear(4, 2024)))
            groups.add(ImageGroup("March", dao.findByMonthAndYear(3, 2024)))
            return groups
        }
    }
}