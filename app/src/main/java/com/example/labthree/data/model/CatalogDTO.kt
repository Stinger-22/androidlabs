package com.example.labthree.data.model

import com.google.gson.annotations.SerializedName

class CatalogDTO(
    @SerializedName("title" ) var title : String,
    @SerializedName("n"     ) var n     : Int
) {
    override fun toString(): String {
        return "CatalogDTO(title='$title', n=$n)"
    }
}