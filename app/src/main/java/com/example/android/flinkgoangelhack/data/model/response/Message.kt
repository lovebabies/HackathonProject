package com.example.android.flinkgoangelhack.data.model.response

data class Message(var createdTime: Long? = 0,
                   var content: String = "",
                   var userType: Int = 0,
                   var displayAvatar: Boolean = true,
                   var isTyped: Boolean = false)