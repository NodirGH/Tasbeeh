package com.example.tasbeeh

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasbeeh.databinding.InsideItemsBinding

class ItemsInside : AppCompatActivity() {
    private lateinit var binding: InsideItemsBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        binding = InsideItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val word = intent.getParcelableExtra<Data>("word")
        if (word != null){
            binding.tasbehWordInside.text = word.tasbehWord
            binding.ivInside.setImageResource(word.imageInt)
        }
    }
}