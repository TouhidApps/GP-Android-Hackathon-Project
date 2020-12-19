package com.touhidapps.hackathonproject.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.multidex.MultiDexApplication
import com.getkeepsafe.relinker.ReLinker

class App : MultiDexApplication() {

    companion object {
        lateinit var app: App

        fun isNetworkAvailable(): Boolean {
            var haveConnectedWifi = false
            var haveConnectedMobile = false

            val cm = app.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val netInfo = cm!!.allNetworkInfo
            for (ni in netInfo) {
                if (ni.typeName.equals("WIFI", ignoreCase = true)) {

                }
                if (ni.isConnected) {
                    haveConnectedWifi = true
                }
                if (ni.typeName.equals("MOBILE", ignoreCase = true)) {

                }
                if (ni.isConnected) {
                    haveConnectedMobile = true
                }
            }
            return haveConnectedWifi || haveConnectedMobile
        } // isNetworkAvailable

    } // object



    override fun onCreate() {
        super.onCreate()
        app = this

        ReLinker.loadLibrary(this, "native-lib")


    }



}