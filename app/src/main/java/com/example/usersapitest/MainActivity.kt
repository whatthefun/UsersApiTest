package com.example.usersapitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usersapitest.data.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList.adapter = UsersListAdapter(this)
        userList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        CoroutineScope(Dispatchers.Main).launch {
            val data = Server.getUsers()
            (userList.adapter as UsersListAdapter).setData(data)
        }
    }
}