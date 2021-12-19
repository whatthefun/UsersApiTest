package com.example.usersapitest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList.adapter = AccountListAdapter(this, listCallback)
        userList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        CoroutineScope(Dispatchers.Main).launch {
            val data = Server.getAccounts()
            data?.let {
                (userList.adapter as AccountListAdapter).setData(it)
            } ?: Toast.makeText(
                this@MainActivity,
                "Fetch data error! Try it later...",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private val listCallback = object : AccountListListener {
        override fun itemClick(account: String?) {
            CoroutineScope(Dispatchers.Main).launch {
                account?.apply {
                    val data = Server.getUserInfo(this)
                    data?.let {
                        UserInfoDialogFragment(it).show(supportFragmentManager, "userInfo")
                    } ?: Toast.makeText(
                        this@MainActivity,
                        "Fetch data error! Try it later...",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}