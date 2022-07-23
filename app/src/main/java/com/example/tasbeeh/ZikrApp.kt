package com.example.tasbeeh

import android.app.Application
import com.example.tasbeeh.data.local.ZikrLocal

class ZikrApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ZikrLocal.initDatabase(this)
    }

}