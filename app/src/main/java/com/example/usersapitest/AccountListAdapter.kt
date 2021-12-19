package com.example.usersapitest

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.example.usersapitest.data.Account


class AccountListAdapter(private val context: Context, private val callback: AccountListListener) :
    RecyclerView.Adapter<UserItemViewHolder>() {

    private var data: List<Account> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_user_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val user = data[position]

        val options: RequestOptions = RequestOptions()
            .circleCrop()
            .placeholder(R.drawable.baseline_portrait_24)
            .error(R.drawable.baseline_portrait_24)

        Glide.with(context).load(GlideUrl(user.avatar_url)).apply(options)
            .into(holder.userProfileImageView)
        holder.userName.text = user.login
        holder.itemView.setOnClickListener {
            callback.itemClick(user.login)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Account>) {
        this.data = data
        notifyDataSetChanged()
    }
}

class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userProfileImageView: ImageView = itemView.findViewById(R.id.userProfileImageView)
    val userName: TextView = itemView.findViewById(R.id.userName)
}

interface AccountListListener {
    fun itemClick(account: String?)
}