package com.example.android.flinkgoangelhack.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginInfo(@SerializedName("expireTime") val expireTime: Long,
                     @SerializedName("accessToken") val accessToken: String,
                     @SerializedName("user") val user: AuthenticateUser,
                     @SerializedName("refreshToken")  val refreshToken: String)