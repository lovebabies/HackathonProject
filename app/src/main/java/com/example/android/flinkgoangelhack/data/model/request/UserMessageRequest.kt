package com.example.android.flinkgoangelhack.data.model.request

import com.google.gson.annotations.SerializedName

data class UserMessageRequest(@SerializedName("message") val message: String)