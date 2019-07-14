package com.example.android.flinkgoangelhack.util

object Role {
    const val USER = 0
    const val BOT = 1
}

object Api {
    const val LOGIN = "login"
    const val CHAT = "webhooks/rest/webhook"
}

object BotAsking {
    const val GREETING = " Hê nhô  \n Cậu cần cho tớ biết vài thông tin để sử dụng app.\n Cậu sẵn sàng chứ?"
    const val NAME_QUESTION = "Tên ông bà già đặt cho c là gì nhỉ?"
    const val GENDER_QUESTION = "C là đực hay cái?"
    const val AGE_QUESTION = "C bao nhiêu tuổi?"
    const val USER_NAME_QUESTION = "Nick name mà c muốn là gì (trong app này)?"
    const val PASSWORD_QUESTION = "Mật khẩu c muốn là gì? \n Đừng lo, tớ sẽ giữ bí mật :))"
    const val ADDRESS_QUESTION = "C đang ở đâu nhỉ? tớ cần biết để cho cậu những thông tin tốt nhất :)) ?"
    const val SUBMIT = "OK, C chắc chắn về những thông tin vừa điền chứ :)) (có hoặc ko)?"
}

object AuthenticationStatus {
    const val NOT_YET = 0
    const val GET_NAME = 1
    const val GET_GENDER = 2
    const val GET_AGE = 3
    const val GET_USER_NAME = 4
    const val GET_PASSWORD = 5
    const val GET_ADDRESS = 6
    const val LET_DONE_REGISTER = 7
    const val DONE_REGISTER = 8
}

object GENDER {
    const val MALE = 0
    const val FEMALE = 1
    const val OTHER = 2
}

object MISS {
    const val type1 = "gõ thế người còn ko hiểu thì AI hiểu cái bếch nào được"
    const val type2 = "Hiện anh đang chưa kiếm được dữ liệu nhé"
    const val type3 = "c nói ngu quá t ko hiểu đc"
    const val type4 = "éo hiểu, thôi làm gì tự làm đi nhé :))"
    const val miss_age = "Có cái tuổi mà ếch biết viết số, ngu vl"
    const val miss_yes_no_question = "phải điền ok, yes hoặc y nhé. Lúc nào đồng ý ms tiếp tục nhé :))"
    const val miss_gender = "không phải nam không phải nữ thì tự hiểu là LGBT nhé :))"
}

const val LAUNCHER_SCREEN_DELAY : Long = 2000