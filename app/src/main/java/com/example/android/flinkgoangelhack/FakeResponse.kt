package com.example.android.flinkgoangelhack

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
}