package com.example.android.flinkgoangelhack.data.model.response

data class User(val address: UserAddress,
                val age: Int = 0,
                val contactInfo: UserContactInfo,
                val avatar: String = "",
                val level: Int = 0,
                val name: String = "",
                val password: String = "",
                val sex: Int = 0,
                val userName: String = "")