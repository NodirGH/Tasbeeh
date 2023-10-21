package my.first.tasbeh.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import my.first.tasbeh.data.mapper.ZikrMapper
import my.first.tasbeh.model.ZikrInfo
import my.first.tasbeh.ui.counter.CounterActivity
import my.first.tasbeh.utils.toast
import tasbeh.databinding.ActivityMainBinding
import tasbeh.databinding.DialogAddZikrBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var zikrAdapter: ZikrAdapter
    private lateinit var bindingAddDialog: DialogAddZikrBinding
    private val viewModel: MainViewModel by viewModels()

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

        viewModel.errorMessage.observe(this) {
            toast(it)
        }

        viewModel.zikrsLocalLive?.observe(this) { zikrs ->
            zikrAdapter.submitList(ZikrMapper.mapEntitiesToDtos(zikrs))
        }

        viewModel.zikrs.observe(this) { zikrs ->
            zikrAdapter.submitList(zikrs)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = zikrAdapter

        viewModel.isSuccessful.observe(this) { isSuccessful ->
            if (isSuccessful)
                Toast.makeText(this, "Зикр қўшилди", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Хатолик бор", Toast.LENGTH_SHORT).show()

        }
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

        zikrAdapter.callbackID = { id -> alertDialog(id) }

        zikrAdapter.callbackRefresh = { id, _ -> refreshCount(id) }
    }

    private fun alertDialog(id: Int) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Ушбу зикрни ўчирмоқчимисиз?")
        alertDialog.setPositiveButton("Ха") { _, _ ->
            viewModel.deleteZikr(id)
        }
        alertDialog.setNegativeButton("Бекор қилиш") { _, _ -> }
        alertDialog.create().show()
    }

    private fun refreshCount(id: Int) {
        viewModel.refreshZikrsCount(id, 0)
    }
}