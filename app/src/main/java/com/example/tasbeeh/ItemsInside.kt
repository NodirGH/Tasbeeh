package com.example.tasbeeh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasbeeh.databinding.InsideItemsBinding

class ItemsInside : AppCompatActivity() {
    private lateinit var binding: InsideItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InsideItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val word = intent.getParcelableExtra<Data>(MainActivity.INTENT_PARCELABLE)

        if (word != null) {
            binding.tvTasbehWordInside.text = word.tasbehWord
            binding.ivInside.setImageResource(word.imageInt)
        }
    }
}