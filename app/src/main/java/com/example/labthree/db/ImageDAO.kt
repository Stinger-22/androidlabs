package com.example.labthree.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ImageDAO {

    @Query("SELECT * FROM image")
    fun getAll(): List<Image>

    @Query("SELECT * from image WHERE place LIKE :place")
    fun findByPlace(place: String): List<Image>

    @Query("SELECT * from image WHERE title LIKE :title")
    fun findByTitle(title: String): List<Image>

    @Query("SELECT * from image WHERE year = :year AND month = :month")
    fun findByMonthAndYear(month: Int, year: Int): List<Image>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: Image)

    @Update
    fun update(image: Image)

    @Delete
    fun delete(image: Image)
}