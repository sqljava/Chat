package uz.ictschool.chat.helpers

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.ictschool.chat.model.User

class FireBaseHelper {
    companion object{
        val users = FirebaseDatabase.getInstance().reference.child("users")

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







    }


}