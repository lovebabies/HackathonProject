package com.example.android.flinkgoangelhack.ui

import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.flinkgoangelhack.R
import com.example.android.flinkgoangelhack.data.model.response.Message
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import com.example.android.flinkgoangelhack.util.setAnimation
import kotlinx.android.synthetic.main.item_bot_message.view.*

class BotMessageViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    private val mContent = view.findViewById<TextView>(R.id.content)
    private val mTypingView = view.findViewById<ImageView>(R.id.typing)

    init {
    }

    fun bind(message: Message) {
        if (!message.isTyped) {
            mTypingView.visibility = View.VISIBLE
            Glide.with(view.context).asGif().load(R.drawable.typing).into(mTypingView)
            message.isTyped = true
            mContent.visibility = View.GONE
            Handler().postDelayed({
                mTypingView.visibility = View.GONE
                mContent.visibility = View.VISIBLE
                mContent.text = message.content
                setAnimation(mContent)
            }, 2000)
        } else {
            mContent.text = message.content
            setAnimation(mContent)
        }
    }

    fun setMessageTyping() {

    }
}