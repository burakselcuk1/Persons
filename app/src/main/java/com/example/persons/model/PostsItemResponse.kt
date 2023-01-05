package com.example.persons.model

import com.google.gson.annotations.SerializedName


data class PostsItemResponse(
    var body: String,
    @SerializedName("id")
    val id: Int,
    val title: String,
    val userId: Int
)