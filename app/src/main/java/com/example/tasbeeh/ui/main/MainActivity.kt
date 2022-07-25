package com.example.tasbeeh.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasbeeh.data.mapper.ZikrMapper
import com.example.tasbeeh.databinding.ActivityMainBinding
import com.example.tasbeeh.databinding.DialogAddZikrBinding
import com.example.tasbeeh.model.ZikrInfo
import com.example.tasbeeh.ui.counter.CounterActivity
import com.example.tasbeeh.utils.toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var zikrAdapter: ZikrAdapter
    private lateinit var bindingAddDialog: DialogAddZikrBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.insertInitialData()
        viewModel.getZikrsLiveData()

        zikrAdapter = ZikrAdapter()
        zikrAdapter.callback = {
            CounterActivity.startFromMainActivity(this, it)
        }




        viewModel.errorMessage.observe(this, Observer {
            toast(it)
        })

        viewModel.zikrsLocalLive?.observe(this, Observer { zikrs ->
            zikrAdapter.submitList(ZikrMapper.mapEntitiesToDtos(zikrs))
        })


        viewModel.zikrs.observe(this, Observer { zikrs ->
            zikrAdapter.submitList(zikrs)
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = zikrAdapter

        viewModel.isSuccessful.observe(this, Observer { isSuccessful ->
            if (isSuccessful)
                Toast.makeText(this, "Зикр қўшилди", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Хатолик бор", Toast.LENGTH_SHORT).show()

        })
        binding.llAddTasbehWord.setOnClickListener {
            bindingAddDialog =
                DialogAddZikrBinding.inflate(LayoutInflater.from(this), binding.root, false)
            val mBuilder = AlertDialog.Builder(this)
                .setView(bindingAddDialog.root)
            val mAlertDialog = mBuilder.show()

            bindingAddDialog.btnAddAlertDialog.setOnClickListener {

                if (bindingAddDialog.etEnterArabicWord.text.isNotEmpty()
                    && bindingAddDialog.etEnterMeaningWord.text.isNotEmpty()
                    && bindingAddDialog.etEnterZikrWord.text.isNotEmpty()
                ) {
                    viewModel.addZikr(
                        ZikrInfo(
                            arabicWord = bindingAddDialog.etEnterArabicWord.text.toString(),
                            zikr = bindingAddDialog.etEnterZikrWord.text.toString(),
                            translation = bindingAddDialog.etEnterMeaningWord.text.toString()
                        )
                    )
                    mAlertDialog.dismiss()
                } else {

                    if (bindingAddDialog.etEnterArabicWord.text.isEmpty()) {
                        bindingAddDialog.etEnterArabicWord.error = "Арабча матнни киритинг"
                    }

                    if (bindingAddDialog.etEnterZikrWord.text.isEmpty()) {
                        bindingAddDialog.etEnterZikrWord.error = "Кирилча матнни киритинг"
                    }

                    if (bindingAddDialog.etEnterMeaningWord.text.isEmpty()) {
                        bindingAddDialog.etEnterMeaningWord.error = "Маъносини киритинг"
                    }
                }
            }

                bindingAddDialog.btnBackAlertDialog.setOnClickListener {
                    mAlertDialog.dismiss()
                }


        }
    }
}