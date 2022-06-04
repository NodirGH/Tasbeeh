package com.example.tasbeeh

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasbeeh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val wordList = listOf(
            Data(R.drawable.heaven, "Субханноллох", "Маъноси: Аллохни поклаб ёд киламин"),
            Data(R.drawable.heaven, "Алхамдулиллах", "Маъноси: Аллохга хамд бўлсин"),
            Data(R.drawable.heaven, "Аллоху акбар", "Маъноси: Аллох буюкдир"),
            Data(R.drawable.heaven, "Астагфируллох", "Маъноси: Аллох кечирсин"),
            Data(R.drawable.heaven, "Астагфируллох ваатубу илайх", "Маъноси: Аллохга истиғфор айтиб тавба қиламан"),
            Data(R.drawable.heaven, "Субханноллохи ва бихамдихи субхоналлохил ъазиймн", "Маъноси: Аллохга хамд айтиш билан Уни айбу нуқсонлардан поклаб ёд этаман"),
            Data(R.drawable.heaven, "Йа мукаллибал кулуб саббит колбий ъала дийник", "Маъноси: Эй қалбларни ўзгартирувчи, қалбимни динингда собит қил"),
            Data(R.drawable.heaven, "Ла илаха илла анта субханака инний кунту миназ злимийн", "Маъноси: Сендан бошқа илоҳ йўқ. Сени поклаб ёд этурман. Албатта мен золимлардан бўлдим"),
            Data(R.drawable.heaven, "Астагфируллохаллазий ла илаха илла хувал хаййул каййум ва атубу илайх", "Маъноси: Бархаёт,тирик Аллоҳдан авф этишини сўрайман ва Унга тавба қиламан")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter =Adapter(this, wordList){
        val intent =Intent(this, ItemsInside::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
        startActivity(intent)
        }
    }
}