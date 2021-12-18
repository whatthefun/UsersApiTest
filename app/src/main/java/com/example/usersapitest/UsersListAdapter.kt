package com.example.usersapitest

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
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity





class UsersListAdapter(private val context: Context, private val data: ArrayList<User>) :
    RecyclerView.Adapter<UserItemViewHolder>() {
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

        Glide.with(context).load(GlideUrl(user.profileImageLink)).apply(options)
            .into(holder.userProfileImageView)
        holder.userName.text = user.name
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(user.githubUrl)))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userProfileImageView: ImageView = itemView.findViewById(R.id.userProfileImageView)
    val userName: TextView = itemView.findViewById(R.id.userName)
}

data class User(
    val name: String,
    val profileImageLink: String,
    val githubUrl: String
)