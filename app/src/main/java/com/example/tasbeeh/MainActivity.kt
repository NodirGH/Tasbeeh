package com.example.tasbeeh

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasbeeh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<Data>
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        list = ArrayList()

        list.add(Data(R.drawable.heaven, "Субханноллох"))
        list.add(Data(R.drawable.heaven, "Алхамдулиллах"))
        list.add(Data(R.drawable.heaven, "Аллоху акбар"))
        list.add(Data(R.drawable.heaven, "Астагфируллох"))
        list.add(Data(R.drawable.heaven, "Астагфируллох ваатубу илайх"))
        list.add(Data(R.drawable.heaven, "Субханноллохи ва бихамдихи субхоналлохил ъазиймн"))
        list.add(Data(R.drawable.heaven, "Йа мукаллибал кулуб саббит колбий ъала дийник"))
        list.add(Data(R.drawable.heaven, "Ла илаха илла анта субханака инний кунту миназ злимийн"))
        list.add(Data(R.drawable.heaven, "Астагфируллохаллазий ла илаха илла хувал хаййул каййум ва атубу илайх"))

        adapter = Adapter(list)
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this, ItemsInside::class.java)
            intent.putExtra("word",it)
            startActivity(intent)
        }
    }
}