package com.example.usersapitest

import android.util.Log
import android.widget.Toast
import com.example.usersapitest.data.Account
import com.example.usersapitest.data.User
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.withContext

object Server {
    private const val TAG = "Server"
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

    suspend fun getAccounts(): List<Account>? = withContext(Dispatchers.IO) {
        val response = service.getAccounts()
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            Log.e(TAG, "getAccounts error: ${response.errorBody()?.charStream()?.readText()}")
            return@withContext null
        }
    }

    suspend fun getUserInfo(account: String): User? = withContext(Dispatchers.IO) {
        val response = service.getUserDetail(account)
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            Log.e(TAG, "getUserInfo error: ${response.errorBody()?.charStream()?.readText()}")
            return@withContext null
        }
    }
}