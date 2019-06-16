package com.example.android.flinkgoangelhack.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(@SerializedName("userName") val userName: String,
                        @SerializedName("password") val password: String)