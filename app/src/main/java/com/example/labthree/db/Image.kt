package com.example.labthree.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

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
    override fun toString(): String {
        return "Image(id=$id, title='$title', description='$description', year=$year, month=$month, day=$day, size=$size, place='$place')"
    }
}