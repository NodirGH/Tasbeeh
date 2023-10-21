package my.first.tasbeh.data.mapper

import my.first.tasbeh.data.local.ZikrEntity
import my.first.tasbeh.model.ZikrInfo

class ZikrMapper {
    companion object {
        private fun mapEntityToDto(zikrEntity: ZikrEntity): ZikrInfo {  //this function is made private
            return ZikrInfo(
                id = zikrEntity.id ?: 0,
                zikr = zikrEntity.zikr ?: "",
                translation = zikrEntity.translation ?: "",
                arabicWord = zikrEntity.arabicWord ?: "",
                counter = zikrEntity.counter ?: 0,
                isDeletable = zikrEntity.isDeletable ?: false,
                zikrAudio = zikrEntity.zikrAudio ?: ""
            )
        }

        fun mapEntitiesToDtos(zikrEntities: List<ZikrEntity>): List<ZikrInfo> {
            return zikrEntities.map { mapEntityToDto(it) }
        }
    }
}