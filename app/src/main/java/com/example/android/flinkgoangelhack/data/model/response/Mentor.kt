package com.example.android.flinkgoangelhack.data.model.response

import com.google.gson.annotations.SerializedName

data class Mentor(@SerializedName("name")val name: String = "",
                  @SerializedName("contactInfo")val contactInfo: MentorContactInfo? = null,
                  @SerializedName("level")val level: Int = 0,
                  @SerializedName("address") val address: UserAddress,
                  @SerializedName("organization") val organization: String)