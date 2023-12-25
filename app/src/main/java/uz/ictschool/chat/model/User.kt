package uz.ictschool.chat.model

data class User(
    var username: String?,
    var password: String?,
    var firstName: String?,
    var lastName: String?,
    var key:String?
){
    constructor():this(null, null, null, null, null)
}
