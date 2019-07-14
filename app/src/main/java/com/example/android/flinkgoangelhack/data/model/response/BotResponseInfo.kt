package com.example.android.flinkgoangelhack.data.model.response

import com.google.gson.annotations.SerializedName

data class BotResponseInfo(@SerializedName("text") val text: String,
                           @SerializedName("recipient_id") val recipient_id: String)