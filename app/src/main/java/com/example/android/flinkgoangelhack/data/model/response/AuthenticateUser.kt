package com.example.android.flinkgoangelhack.data.model.response

import com.google.gson.annotations.SerializedName

data class AuthenticateUser(@SerializedName("userId") val userId: String,
                            @SerializedName("userName") val userName: String,
                            @SerializedName("userAvatar") val userAvatar: String)