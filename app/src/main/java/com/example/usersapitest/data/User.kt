package com.example.usersapitest.data

data class User(
    val name: String,
    val login: String,
    val followers: Int = 0,
    val following: Int = 0,
    val updated_at: String?= null,
    val avatar_url: String? = null,
    val html_url: String? = null
)
