package my.first.tasbeh.data.local

import android.content.Context
import androidx.room.Room

class ZikrLocal {
    companion object {
        lateinit var zikrDatabase: ZikrDatabase

        fun initDatabase(applicationContext: Context) {
            zikrDatabase = Room.databaseBuilder(
                applicationContext,
                ZikrDatabase::class.java, "zikr-db"
            )
                .allowMainThreadQueries() // TODO remove to use Coroutines
                .build()
        }
    }
}