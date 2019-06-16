package com.example.android.flinkgoangelhack

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.flinkgoangelhack.util.Role.BOT
import com.example.android.flinkgoangelhack.data.model.response.Message
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import com.example.android.flinkgoangelhack.util.Role.USER
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject

class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    var mContent = itemView.findViewById<TextView>(R.id.content)
    var mAvatar = itemView.findViewById<CircleImageView>(R.id.avatar)
    fun bind(message: Message, context: Context, position: Int, lastPos: Int, previousType: Int, mPref: PreferencesUtil) {
        if (message.userType == USER) {
            Glide.with(context).load(mPref.avatarUrl).into(mAvatar)
        }
        if (message.displayAvatar) {
            mAvatar.visibility = View.VISIBLE
        } else {
            mAvatar.visibility = View.GONE
        }
        if (position == lastPos) {
            message.displayAvatar = previousType != message.userType
            if (message.userType == BOT) {
                val typingView = itemView.findViewById<ImageView>(R.id.typing)
                typingView.visibility = View.VISIBLE
                Glide.with(context).asGif().load(R.raw.type).into(typingView)
                mContent.visibility = View.GONE
                Handler().postDelayed({
                    typingView.visibility = View.GONE
                    mContent.visibility = View.VISIBLE
                    mContent.text = message.content
                }, 2000)
            } else {
                mContent.text = message.content
            }
        } else {
            mContent.text = message.content
        }
    }
}