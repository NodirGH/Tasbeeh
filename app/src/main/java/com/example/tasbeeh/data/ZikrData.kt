package com.example.tasbeeh.data

class ZikrData {
    companion object {
        fun getZikrs(): List<ZikrInfo> {
            return listOf(
                ZikrInfo(1, "Субханноллох", isDeletable = false),
                ZikrInfo(2, "Алхамдулиллах",isDeletable = false),
                ZikrInfo(3, "Аллоху акбар", isDeletable = false),
                ZikrInfo(4, "Астагфируллох", isDeletable = false),
                ZikrInfo(5, "Астагфируллох ваатубу илайх", isDeletable = false),
                ZikrInfo(6, "Субханноллохи ва бихамдихи субхоналлохил ъазиймн", isDeletable = false),
                ZikrInfo(7, "Йа мукаллибал кулуб саббит колбий ъала дийник", isDeletable = false),
                ZikrInfo(8, "Ла илаха илла анта субханака инний кунту миназ злимийн", isDeletable = false),
                ZikrInfo(9, "Астагфируллохаллазий ла илаха илла хувал хаййул каййум ва атубу илайх", isDeletable = false)
            )
        }
    }
}