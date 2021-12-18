package com.example.usersapitest

import com.example.usersapitest.data.User
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.withContext

object Server {
    private const val Github_Api_Url = "https://api.github.com/"
    private val service: ApiService

    init {
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Github_Api_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        service = retrofit.create(ApiService::class.java)
    }

    suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        val response = service.getUsers()
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            throw Exception(response.errorBody()?.charStream()?.readText())
        }
    }
}