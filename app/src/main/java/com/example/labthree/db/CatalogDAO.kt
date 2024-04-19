package com.example.labthree.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CatalogDAO {
    @Query("SELECT * FROM catalog")
    fun getAll(): List<Catalog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(catalog: Catalog)

    @Update
    fun update(catalog: Catalog)

    @Delete
    fun delete(catalog: Catalog)
}