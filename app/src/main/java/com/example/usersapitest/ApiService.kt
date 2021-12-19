package com.example.usersapitest

import com.example.usersapitest.data.Account
import com.example.usersapitest.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun getAccounts(): Response<List<Account>>

    @GET("/users/{account}")
    suspend fun getUserDetail(@Path ("account") account: String): Response<User>
}