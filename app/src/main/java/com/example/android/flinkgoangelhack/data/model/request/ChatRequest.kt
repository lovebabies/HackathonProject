package com.example.android.flinkgoangelhack.data.model.request

import com.google.gson.annotations.SerializedName

data class ChatRequest(@SerializedName("message") val message: String)