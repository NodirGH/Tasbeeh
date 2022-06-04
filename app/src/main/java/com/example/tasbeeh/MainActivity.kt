package com.example.tasbeeh

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            Data(R.drawable.heaven, "Субханноллох"),
            Data(R.drawable.heaven, "Алхамдулиллах"),
            Data(R.drawable.heaven, "Аллоху акбар"),
            Data(R.drawable.heaven, "Астагфируллох"),
            Data(R.drawable.heaven, "Астагфируллох ваатубу илайх"),
            Data(R.drawable.heaven, "Субханноллохи ва бихамдихи субхоналлохил ъазиймн"),
            Data(R.drawable.heaven, "Йа мукаллибал кулуб саббит колбий ъала дийник"),
            Data(R.drawable.heaven, "Ла илаха илла анта субханака инний кунту миназ злимийн"),
            Data(R.drawable.heaven, "Астагфируллохаллазий ла илаха илла хувал хаййул каййум ва атубу илайх")
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