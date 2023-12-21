package uz.ictschool.chat.model

data class User(
    var username: String,
    var password: String,
    var key:String? = null
)