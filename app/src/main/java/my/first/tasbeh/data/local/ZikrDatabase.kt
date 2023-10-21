package my.first.tasbeh.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ZikrEntity::class], version = 1)
abstract class ZikrDatabase : RoomDatabase() {
    abstract fun zikrDao(): ZikrDao
}