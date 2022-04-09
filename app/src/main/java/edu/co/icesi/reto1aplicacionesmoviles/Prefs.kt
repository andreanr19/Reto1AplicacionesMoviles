package edu.co.icesi.reto1aplicacionesmoviles

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post
import java.lang.Exception

class Prefs(val context: Context) {

    val SHARED_NAME ="Mydtb"
    val SHARED_NAME_ALFA ="nameAlfa"
    val SHARED_NAME_BETA ="nameBeta"
    val SHARED_USERNAME_ALFA = "usernameAlfa"
    val SHARED_USERNAME_BETA = "usernameBeta"
    val SHARED_BIO_ALFA = "bioAlfa"
    val SHARED_BIO_BETA = "bioBeta"
    val SHARED_PASSWORD = "password"
    val SHARED_REMEMBER = "remember"
    val SHARED_ISBETA ="isbeta"
    val SHARED_POSTS = "posts"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    val gson = Gson()

    fun saveUsernameAlfa(username:String){
        storage.edit().putString(SHARED_USERNAME_ALFA, username).apply()
    }

    fun saveNameAlfa(name:String){
        storage.edit().putString(SHARED_NAME_ALFA, name).apply()
    }
    fun saveBioAlfa(bio:String){
        storage.edit().putString(SHARED_BIO_ALFA, bio).apply()
    }
    fun saveBioBeta(bio:String){
        storage.edit().putString(SHARED_BIO_BETA, bio).apply()
    }

    fun saveNameBeta(name:String){
        storage.edit().putString(SHARED_NAME_BETA, name).apply()
    }

    fun saveUsernameBeta(username:String){
        storage.edit().putString(SHARED_USERNAME_BETA, username).apply()
    }

    fun savePassword(password:String){
        storage.edit().putString(SHARED_PASSWORD, password).apply()
    }

    fun saveRemember(remember:Boolean){
        storage.edit().putBoolean(SHARED_REMEMBER, remember).apply()
    }

    fun saveIsbeta(isbeta:Boolean){
        storage.edit().putBoolean(SHARED_ISBETA, isbeta).apply()
    }

    fun getUsernameAlfa():String{
        return storage.getString(SHARED_USERNAME_ALFA, "")!!
    }

    fun getBioAlfa():String{
        return storage.getString(SHARED_BIO_ALFA,"")!!
    }
    fun getBioBeta():String{
        return storage.getString(SHARED_BIO_BETA,"")!!
    }

    fun getNameAlfa():String{
        return storage.getString(SHARED_NAME_ALFA, "")!!
    }

    fun getNameBeta():String{
        return storage.getString(SHARED_NAME_BETA, "")!!
    }

    fun getUsernameBeta():String{
        return storage.getString(SHARED_USERNAME_BETA,"")!!
    }

    fun getPassword():String{
        return storage.getString(SHARED_PASSWORD,"")!!
    }

    fun getRemember(): Boolean{
        return storage.getBoolean(SHARED_REMEMBER, false)
    }

    fun getIsbeta(): Boolean{
        return storage.getBoolean(SHARED_ISBETA, false)
    }

    fun prefsClean(){
        val editor : SharedPreferences.Editor = storage.edit()
        editor.clear()
        editor.apply()
    }

    fun getEditor(): SharedPreferences.Editor {
        val editor : SharedPreferences.Editor = storage.edit()
        return editor
    }

    fun savePosts(post: Post){
       var posts = getPosts()
        posts.add(post)

        storage.edit().putString(SHARED_POSTS, gson.toJson(posts)).apply()

    }



    fun getPosts() : MutableList<Post>{
        var postList = mutableListOf<Post>()
        val posts = storage.getString(SHARED_POSTS,"")
        Log.e(">>>", posts!!)

        if(posts!!.isEmpty()) return postList
        postList = gson.fromJson(posts, Array<Post>::class.java).toMutableList()
        return postList
    }
}