package my.first.tasbeh.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import my.first.tasbeh.data.local.ZikrEntity

@Parcelize
data class ZikrInfo(
    val id: Int = 0,
    val zikr: String = "",
    val translation: String = "",
    val arabicWord: String = "",
    val counter: Int = 0,
    var isDeletable: Boolean = true,
    var zikrAudio: String = ""
) : Parcelable {
    fun mapToEntity(): ZikrEntity {
        return ZikrEntity(
            zikr = zikr,
            translation = translation,
            arabicWord = arabicWord,
            counter = counter,
            isDeletable = isDeletable,
            zikrAudio = zikrAudio
        )
    }
}