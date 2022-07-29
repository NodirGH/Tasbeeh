package com.example.tasbeeh.data

import com.example.tasbeeh.model.ZikrInfo

class ZikrData {
    companion object {
        fun getZikrs(): List<ZikrInfo> {
            return listOf(
                ZikrInfo(
                    1,
                    "Субханноллох",
                    "Аллоҳни поклаб ёд қиламан",
                    "سُبْحَانَ ٱللَّٰه",
                    isDeletable = false,
                    zikrAudio = "subhanolloh.mp3"
                ),
                ZikrInfo(
                    2,
                    "Алхамдулиллах",
                    "Аллоҳга хамд бўлсин",
                    "ٱلْحَمْدُ لِلّٰه",
                    isDeletable = false,
                    zikrAudio = "alhamdulillah.mp3"
                ),
                ZikrInfo(
                    3,
                    "Аллоху акбар",
                    "Аллоҳ буюкдир",
                    "اللَّهُ أَكْبَر",
                    isDeletable = false,
                    zikrAudio = "allohu_akbar.mp3"
                ),
                ZikrInfo(
                    4,
                    "Астагфируллох",
                    "Аллоҳ кечирсин",
                    "َسْتَغْفِرُ اللّٰه",
                    isDeletable = false,
                    zikrAudio = "astagfirulloh.mp3"
                ),
                ZikrInfo(
                    5,
                    "Астагфируллох ваатубу илайх",
                    "Аллоҳга истиғфор айтиб тавба қиламан",
                    "أَسْـتَـغْـفِـرُ اللهَ وَ أَ تُـوبُ إِ لَـْيه",
                    isDeletable = false,
                    zikrAudio = "astahfiruloh_vatubu_ilayh.mp3"
                ),
                ZikrInfo(
                    6,
                    "Субханноллохи ва бихамдихи субхоналлохил ъазиймн",
                    "Аллоҳга хамд айтиш билан Уни айбу нуқсонлардан поклаб ёд этаман",
                    "سُبْحَانَ اللّهِ وَ بِحَمْدِهِ ، سُبْحَانَ اللّهِ الْعَظِيم",
                    isDeletable = false,
                    zikrAudio = "subhanollohi_va_bihamdihi.mp3"
                ),
                ZikrInfo(
                    7,
                    "Йа мукаллибал кулуб саббит колбий ъала дийник",
                    "Эй қалбларни ўзгартирувчи, қалбимни динингда собит қил",
                    "يامقلب القلوب ثبت قلبي على دينك",
                    isDeletable = false,
                    zikrAudio = "ya_muqollibal_qulub.mp3"
                ),
                ZikrInfo(
                    8,
                    "Ла илаха илла анта субханака инний кунту миназ злимийн",
                    "Сендан бошқа илоҳ йўқ. Сени поклаб ёд этурман. Албатта мен золимлардан бўлдим",
                    "لَّآ إِلَٰهَ إِلَّآ أَنتَ سُبْحَٰنَكَ إِنِّى كُنتُ مِنَ ٱلظَّٰلِمِين",
                    isDeletable = false,
                    zikrAudio = "la_ilaha_illa_anta_subhanaka.mp3"
                ),
                ZikrInfo(
                    9,
                    "Астагфируллохаллазий ла илаха илла хувал хаййул каййум ва атубу илайх",
                    "Барҳаётб, тирик Аллоҳдан авф этишини сўрайман ва Унга тавба қиламан",
                    "أسْتَغْفِرُ اللهَ الَّذِي لا إلَهَ إلا هُوَ الحَيُّ القَيُومُ وَأتُوبُ إلَيه",
                    isDeletable = false,
                    zikrAudio = "astahfirullohallaziy_la_illaha.mp3"
                )

                )
        }
    }
}