package com.example.tasbeeh.data.mapper

import com.example.tasbeeh.data.local.ZikrEntity
import com.example.tasbeeh.model.ZikrInfo

class ZikrMapper {
    companion object {
        fun mapEntityToDto(zikrEntity: ZikrEntity): ZikrInfo {
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