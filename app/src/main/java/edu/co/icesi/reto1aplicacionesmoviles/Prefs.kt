package edu.co.icesi.reto1aplicacionesmoviles

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post
import edu.co.icesi.reto1aplicacionesmoviles.Model.User
import java.lang.Exception
import java.util.*

class Prefs(val context: Context) {

    val SHARED_NAME ="Mydtb"
    val SHARED_POSTS = "posts"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)
    var userId : String? = null
    var LoggedUser = "LOGGED_USER"
    val SHARED_REMEMBER = "remember"
    val gson = Gson()

    /*
    val SHARED_NAME_ALFA ="nameAlfa"
    val SHARED_NAME_BETA ="nameBeta"
    val SHARED_USERNAME_ALFA = "usernameAlfa"
    val SHARED_USERNAME_BETA = "usernameBeta"
    val SHARED_BIO_ALFA = "bioAlfa"
    val SHARED_BIO_BETA = "bioBeta"
    val SHARED_PASSWORD = "password"
    val SHARED_ISBETA ="isbeta"*/


    fun createUser(){
        if(storage.getString("ALFA","").toString().isEmpty()||storage.getString("BETA","").toString().isEmpty()){

            val user = User(UUID.randomUUID().toString(),"Alfa","alfanunez","Hola","")
            val user2 = User(UUID.randomUUID().toString(),"Beta","betanunez","Hola","")
            val userString = gson.toJson(user)
            val userString2 = gson.toJson(user2)
            storage.edit().putString("ALFA", userString).apply()
            storage.edit().putString("BETA", userString2).apply()
        }

        else return
    }

    fun LogUser(user : String){
        storage.edit().putString(LoggedUser, user).apply()
    }

    fun getLoggedUser(): User? {

        userId = storage.getString(LoggedUser,"").toString()

        if(userId != null){
            val userString = storage.getString(userId,"")
            return gson.fromJson(userString, User::class.java)
        }

        else return null
    }

    fun updateUser(user: User){

        val userString = gson.toJson(user)
        storage.edit().putString(userId,userString).apply()
    }

    fun logOut(){

        storage.edit().remove(LoggedUser).apply()
    }


    fun getUserById(uuid: String):User{

        val user1 = gson.fromJson(storage.getString("ALFA",""),User::class.java)
        val user2 = gson.fromJson(storage.getString("BETA",""),User::class.java)

        if(user1.id.contentEquals(uuid)) return user1

        return user2


    }
    fun saveRemember(remember:Boolean){
        storage.edit().putBoolean(SHARED_REMEMBER, remember).apply()
    }
    fun getRemember(): Boolean{
        return storage.getBoolean(SHARED_REMEMBER, false)
    }
    /*
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



    fun getIsbeta(): Boolean{
        return storage.getBoolean(SHARED_ISBETA, false)
    }

    fun prefsClean(){
        val editor : SharedPreferences.Editor = storage.edit()
        editor.clear()
        editor.apply()
    }*/



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