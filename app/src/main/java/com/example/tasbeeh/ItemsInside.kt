package com.example.tasbeeh

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemsInside : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inside_items)

        val word = intent.getParcelableExtra<Data>(MainActivity.INTENT_PARCELABLE)

        val ivInside = findViewById<ImageView>(R.id.ivInside)
        val tasbehWordInside = findViewById<TextView>(R.id.tvTasbehWordInside)

        if (word != null) {
            tasbehWordInside.text = word.tasbehWord
            ivInside.setImageResource(word.imageInt)
        }
    }
}