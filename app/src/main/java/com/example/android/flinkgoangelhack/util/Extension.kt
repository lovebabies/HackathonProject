package com.example.android.flinkgoangelhack.util

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.android.flinkgoangelhack.R


fun RecyclerView.ViewHolder.setAnimation(viewToAnimate: View) {
    val anim = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.anim_message_appear)
    viewToAnimate.startAnimation(anim)
}