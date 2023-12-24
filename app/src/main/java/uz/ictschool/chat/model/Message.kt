package uz.ictschool.chat.model

data class Message(
    var text: String? = null,
    var from: String? = null,
    var to: String? = null,
    var key: String? = null
)