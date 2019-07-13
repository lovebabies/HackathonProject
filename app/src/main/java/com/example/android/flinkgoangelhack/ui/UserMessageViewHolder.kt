package com.example.android.flinkgoangelhack.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.flinkgoangelhack.R
import com.example.android.flinkgoangelhack.data.model.response.Message
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import com.example.android.flinkgoangelhack.util.setAnimation
import kotlinx.android.synthetic.main.item_user_message.view.*

class UserMessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val mContent = itemView.findViewById<TextView>(R.id.content)
    fun bind(message: Message) {
        mContent.text = message.content
        setAnimation(mContent)
    }
}