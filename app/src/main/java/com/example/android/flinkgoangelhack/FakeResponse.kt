package com.example.android.flinkgoangelhack

import com.example.android.flinkgoangelhack.data.model.request.UserMessageRequest
import com.example.android.flinkgoangelhack.data.model.response.BotResponse
import com.example.android.flinkgoangelhack.data.model.response.BotResponseInfo

object FakeResponse {
    val banned_word = arrayListOf("ngu","đần")

    fun response(input: String): String {
        for (i in banned_word) {
            if (input.contains(i)) {
                return "Nói nữa là bố mày đá ra khỏi app"
            }
        }

        return "Chưa có data bạn nhé"
    }

    fun handleFakeRequest(userMessageRequest: UserMessageRequest): BotResponseInfo {
        val content = userMessageRequest.message
        return BotResponseInfo( response(content), null, null)
    }
}