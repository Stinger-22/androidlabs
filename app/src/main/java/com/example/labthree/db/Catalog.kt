package com.example.labthree.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.labthree.api.CatalogDTO

@Entity(tableName = "catalog")
class Catalog (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val n: Int
){
    constructor(catalog: CatalogDTO) : this(0, catalog.title, catalog.n) {
    }

    override fun toString(): String {
        return "Catalog(id=$id, title='$title', n=$n)"
    }
}