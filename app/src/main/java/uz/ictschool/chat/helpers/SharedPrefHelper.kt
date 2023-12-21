package uz.ictschool.chat.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper constructor(context: Context) {

    private val shared: SharedPreferences =
        context.getSharedPreferences("sharedBase", Context.MODE_PRIVATE)
    private val editor = shared.edit()

    private val USER_KEY = "user_key"

    companion object{
        private var instance:SharedPrefHelper? = null
        fun getInstance(context: Context):SharedPrefHelper{
            if (instance == null){
                instance = SharedPrefHelper(context)
            }
            return instance!!
        }
    }

    fun setUserKey(key: String){
        editor.putString(USER_KEY,key).apply()
    }

    fun getUserKey():String{
        return shared.getString(USER_KEY,"")!!
    }
}