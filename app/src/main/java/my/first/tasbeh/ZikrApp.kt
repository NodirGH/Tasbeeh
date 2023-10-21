package my.first.tasbeh

import android.app.Application
import my.first.tasbeh.data.local.ZikrLocal

class ZikrApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ZikrLocal.initDatabase(this)
    }

}