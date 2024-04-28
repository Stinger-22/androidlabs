package com.example.labthree.data.model

import com.google.gson.annotations.SerializedName


data class ImageDTO (

    @SerializedName("title"       ) var title       : String,
    @SerializedName("description" ) var description : String,
    @SerializedName("year"        ) var year        : Int,
    @SerializedName("month"       ) var month       : Int,
    @SerializedName("day"         ) var day         : Int,
    @SerializedName("size"        ) var size        : Float,
    @SerializedName("place"       ) var place       : String

)