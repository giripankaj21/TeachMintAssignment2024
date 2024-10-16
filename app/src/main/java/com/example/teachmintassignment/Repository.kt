package com.example.teachmintassignment

data class Repository(
    val id: Int,
    val name: String,
    val full_name: String,
    val html_url: String,
    val description: String?,
    val owner: Owner
)

data class Owner(
    val login: String,
    val avatar_url: String
)
