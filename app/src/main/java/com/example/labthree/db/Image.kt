package com.example.labthree.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.labthree.data.model.ImageDTO

@Entity(tableName = "image")
class Image (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val size: Float,
    val place: String
){
    constructor(image: ImageDTO) : this(0, image.title, image.description, image.year, image.month, image.day, image.size, image.place) {
    }

    override fun toString(): String {
        return "Image(id=$id, title='$title', description='$description', year=$year, month=$month, day=$day, size=$size, place='$place')"
    }
}