package com.example.usersapitest

import com.example.usersapitest.data.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
}