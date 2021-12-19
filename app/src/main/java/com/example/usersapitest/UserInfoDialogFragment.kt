package com.example.usersapitest

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.example.usersapitest.data.User
import java.text.SimpleDateFormat
import java.util.*

class UserInfoDialogFragment(private val user: User) : DialogFragment() {
    private val TAG = "UserInfoDialogFragment"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val options: RequestOptions = RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.baseline_portrait_24)
                .error(R.drawable.baseline_portrait_24)

            val view = inflater.inflate(R.layout.dialog_user_info, null)
            Glide.with(it).load(GlideUrl(user.avatar_url)).apply(options)
                .into(view.findViewById(R.id.userProfileImageView))
            view.findViewById<TextView>(R.id.userName).text = user.name
            view.findViewById<TextView>(R.id.userAccount).text = user.login
            view.findViewById<TextView>(R.id.followersTxt).text = user.followers.toString()
            view.findViewById<TextView>(R.id.followingTxt).text = user.following.toString()
            view.findViewById<TextView>(R.id.githubTxt).text = user.html_url

            val fromFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val toFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            user.updated_at?.also { userUpdateTime ->
                try {
                    val newDate: Date? = fromFormat.parse(userUpdateTime)
                    view.findViewById<TextView>(R.id.lastUpdateTxt).text = toFormat.format(newDate!!)
                } catch (e: Exception) {
                    Log.e(TAG, e.toString())
                }
            }

            builder.setView(view)
                .setPositiveButton(
                    R.string.ok
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}