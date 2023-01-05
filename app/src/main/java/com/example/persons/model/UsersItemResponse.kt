package com.example.persons.model



data class UsersItemResponse(
    val albumId: Int,
    var name: String?,
    val thumbnailUrl: String,
    val url: String,
    val userId: Int?
)