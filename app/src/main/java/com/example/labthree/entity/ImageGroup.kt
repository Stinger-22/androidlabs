package com.example.labthree.entity

class ImageGroup(
    val name: String,
    val numberOfPhotos: Int
    ) {

    companion object {
        fun createImageGroups(): ArrayList<ImageGroup> {
            val groups = ArrayList<ImageGroup>()
            groups.add(ImageGroup("This month", 6))
            groups.add(ImageGroup("December", 9))
            return groups
        }
    }
}