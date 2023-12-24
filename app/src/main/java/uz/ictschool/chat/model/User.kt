package uz.ictschool.chat.model

data class User(
    var username: String? = null,
    var password: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var key:String? = ""
)
