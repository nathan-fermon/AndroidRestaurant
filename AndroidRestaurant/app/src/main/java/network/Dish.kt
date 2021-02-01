package com.example.isen_2021.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Dish(
    @SerializedName("name_fr") val name: String,
    val ingredients: List<Ingredient>,
    val images: List<String>,
    val prices: List<Price>
): Serializable {
    fun getThumbnailUrl(): String? {
        return if(images.isNotEmpty() && images[0].isNotEmpty()) {
            images[0]
        } else {
            null
        }
    }
}