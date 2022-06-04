package com.example.tasbeeh

import android.content.Intent
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
            binding.tvDivineWordInside.text = word.divineWord
            binding.ivInside.setImageResource(word.imageInt)
        }

        var clickedTimes = 0
        binding.btnForTap.setOnClickListener {
            clickedTimes++
            binding.counter.text = clickedTimes.toString()
        }

        binding.btnRefreshInsider.setOnClickListener {
            clickedTimes = 0
            binding.counter.text = clickedTimes.toString()
        }

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}