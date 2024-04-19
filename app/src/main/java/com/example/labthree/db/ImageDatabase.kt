package com.example.labthree.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Image::class, Catalog::class], version = 1)
abstract class ImageDatabase: RoomDatabase() {
    abstract fun imageDao(): ImageDAO
    abstract fun catalogDao(): CatalogDAO
}