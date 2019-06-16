package com.example.android.flinkgoangelhack.data.model.response

import com.google.gson.annotations.SerializedName

data class BotResponseInfo(@SerializedName("message") val message: String,
                           @SerializedName("mentors") val mentors: ArrayList<Mentor>?,
                           @SerializedName("users") val users: ArrayList<User>?)