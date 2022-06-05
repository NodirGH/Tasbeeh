package com.example.tasbeeh.data.mapper

import com.example.tasbeeh.data.ZikrInfo
import com.example.tasbeeh.data.local.ZikrEntity

class ZikrMapper {
    companion object {
        fun mapEntityToDto(zikrEntity: ZikrEntity): ZikrInfo {
            return ZikrInfo(
                id = zikrEntity.id ?: 0,
                zikr = zikrEntity.zikr ?: "",
                translation = zikrEntity.translation ?: "",
                counter = zikrEntity.counter ?: 0,
                isDeletable = zikrEntity.isDeletable ?: false
            )
        }

        fun mapEntitiesToDtos(zikrEntities: List<ZikrEntity>): List<ZikrInfo> {
            return zikrEntities.map { mapEntityToDto(it) }
        }
    }
}