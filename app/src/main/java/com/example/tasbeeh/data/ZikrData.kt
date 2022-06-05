package com.example.tasbeeh.data

class ZikrData {
    companion object {
        fun getZikrs(): List<ZikrInfo> {
            return listOf(
                ZikrInfo(1, "Субханноллох"),
                ZikrInfo(2, "Алхамдулиллах"),
                ZikrInfo(3, "Аллоху акбар"),
                ZikrInfo(4, "Астагфируллох"),
                ZikrInfo(5, "Астагфируллох ваатубу илайх"),
                ZikrInfo(6, "Субханноллохи ва бихамдихи субхоналлохил ъазиймн"),
                ZikrInfo(7, "Йа мукаллибал кулуб саббит колбий ъала дийник"),
                ZikrInfo(8, "Ла илаха илла анта субханака инний кунту миназ злимийн"),
                ZikrInfo(9, "Астагфируллохаллазий ла илаха илла хувал хаййул каййум ва атубу илайх")
            )
        }
    }
}