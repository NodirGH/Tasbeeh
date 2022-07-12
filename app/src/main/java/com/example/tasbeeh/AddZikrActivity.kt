package com.example.tasbeeh

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tasbeeh.databinding.ActivityAddZikrBinding

class AddZikrActivity : AppCompatActivity() {
    lateinit var arabicText: EditText
    lateinit var russianText: EditText
    lateinit var meaningText: EditText
    lateinit var btnAddZikr: Button
    lateinit var btnCancel: Button
    lateinit var viewModel: TasbehViewModel
    private lateinit var binding: ActivityAddZikrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddZikrBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TasbehViewModel::class.java]

        arabicText = binding.etEnterArabicWord
        russianText = binding.etEnterRussianWord
        meaningText = binding.etEnterMeaningWord
        btnAddZikr = binding.btnAddAlertDialog
        btnCancel = binding.btnBackAlertDialog


    }
}