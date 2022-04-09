package edu.co.icesi.reto1aplicacionesmoviles

import android.app.Application

class Reto1Application:Application() {

    companion object{
        lateinit var prefs : Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
        prefs.createUser()
    }
}