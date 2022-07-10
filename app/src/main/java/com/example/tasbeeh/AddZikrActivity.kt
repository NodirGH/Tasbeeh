package com.example.tasbeeh

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddZikrActivity: AppCompatActivity() {
    lateinit var arabicText: EditText
    lateinit var russianText: EditText
    lateinit var meaningText: EditText
    lateinit var btnAddZikr: Button
    lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_zikr)


    }
}