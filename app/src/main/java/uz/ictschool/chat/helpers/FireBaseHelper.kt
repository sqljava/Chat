package uz.ictschool.chat.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.ictschool.chat.model.User
import java.text.SimpleDateFormat
import java.util.Date

class FireBaseHelper {
    companion object{
        val users = FirebaseDatabase.getInstance().reference.child("users")
        val messages = FirebaseDatabase.getInstance().reference.child("messages")

        fun logIn(userName: String, password: String, logged: (key: String?)->Unit){
            users.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val users = snapshot.children
                    for (u in users){
                        var user = u.getValue(User::class.java)!!
                        if (user.username == userName){
                            if (user.password == password){
                                logged(user.key!!)
                                return
                            }
                        }else{
                            logged(null)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "onCancelled: $error",error.toException())
                }
            })
        }


        fun signUp(user: User,
                   context: Context,
                   success:(Boolean)->Unit){
            val key = users.push().key.toString()
            user.key = key
            users.child(key).setValue(user)
            SharedPrefHelper.getInstance(context).setUserKey(key)
            success(true)
        }

        fun getAllUsers(callback:(users: MutableList<User>)->Unit){

            users.addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var u = snapshot.children
                    var users = mutableListOf<User>()
                    u.forEach{
                        val user = it.getValue(User::class.java)!!
                        users.add(user)
                    }
                    callback(users)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "onCancelled: $error",error.toException())
                }
            })
        }


        fun getAllContacts(key: String,callback:(contacts: MutableList<User>)->Unit){

            users.addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var u = snapshot.children
                    var contacts = mutableListOf<User>()
                    u.forEach{
                        val user = it.getValue(User::class.java)!!
                        if (user.key!=key){
                            contacts.add(user)
                        }
                    }
                    callback(contacts)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "onCancelled: $error",error.toException())
                }
            })
        }


        fun getUser(key: String, callback: (User) -> Unit){
            users.child(key).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    if (user != null){
                        callback(user)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "onCancelled: $error",error.toException())
                }
            })
        }

        fun updateUser(user:User){
            users.child(user.key!!).child("username").setValue(user.username)
            users.child(user.key!!).child("password").setValue(user.password)
            users.child(user.key!!).child("firstName").setValue(user.firstName)
            users.child(user.key!!).child("lastName").setValue(user.lastName)
        }

        @SuppressLint("SimpleDateFormat")
        fun sendMessage(text:String, toKey:String, context: Context){
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            val currentUserKey = SharedPrefHelper.getInstance(context).getUserKey()





        }






    }


}