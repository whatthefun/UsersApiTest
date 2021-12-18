package com.example.usersapitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usersData =
            arrayListOf(User("mojombo", "https://avatars.githubusercontent.com/u/1?v=4", "https://github.com/mojombo"), User("defunkt", "https://avatars.githubusercontent.com/u/2?v=4", "https://github.com/defunkt"))
        userList.adapter = UsersListAdapter(this, usersData)
        userList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}