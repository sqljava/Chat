package uz.ictschool.chat.model

data class Message(
    var text: String?,
    var from: String?,
    var to: String?,
    var date: String?,
    var key: String?
){
    constructor():this(null, null, null, null, null)
}