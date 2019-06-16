package com.example.android.flinkgoangelhack.data.model.response

import com.google.gson.annotations.SerializedName

abstract class BaseResponse<T> (@SerializedName("status") var status: Int = 0,
                                @SerializedName("message") var message: String = "",
                                @SerializedName("responseData") var responseData: T? = null)