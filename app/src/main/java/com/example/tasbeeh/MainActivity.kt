package com.example.tasbeeh

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasbeeh.data.ZikrInfo
import com.example.tasbeeh.data.mapper.ZikrMapper
import com.example.tasbeeh.databinding.ActivityMainBinding
import com.example.tasbeeh.databinding.DialogAddZikrBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var zikrAdapter: ZikrAdapter
    private lateinit var bindingAddDialog: DialogAddZikrBinding
    lateinit var viewModel: TasbehViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        zikrAdapter = ZikrAdapter()
        zikrAdapter.callback = {
            CounterActivity.startFromMainActivity(this, it)
        }

        val tasbehViewModel = TasbehViewModel(application)
        tasbehViewModel.insertInitialData(this)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TasbehViewModel::class.java]

        viewModel.allZikr.observe(this, Observer { zikrs ->
            zikrAdapter.submitList(ZikrMapper.mapEntitiesToDtos(zikrs))
        })


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = zikrAdapter

        //alert dialog
        viewModel.isSuccessful.observe(this, Observer { isSuccessful ->
            if (isSuccessful)
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

        })
        binding.llAddTasbehWord.setOnClickListener {
            bindingAddDialog =
                DialogAddZikrBinding.inflate(LayoutInflater.from(this), binding.root, false)
            val mBuilder = AlertDialog.Builder(this)
                .setView(bindingAddDialog.root)
            val mAlertDialog = mBuilder.show()

            bindingAddDialog.btnAddAlertDialog.setOnClickListener {
                //TODO validate if the input fields are filled with text
                // if not show error for the text field
                /// bindingAddDialog.etEnterArabicWord.error = "Some Error"
                //if(et.isNotFilled){
                //   bindingAddDialog.etEnterArabicWord.error = "Some Error"
                //   return
                //}
                //
                val zikrInfo = ZikrInfo(
//                    if (bindingAddDialog.etEnterArabicWord.text.isNotEmpty() ){
//                        arabicWord = bindingAddDialog.etEnterArabicWord.text.toString()
//                    } else{
//                          Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//                        return@setOnClickListener
//                          }
//                       ,
                    translation = bindingAddDialog.etEnterMeaningWord.text.toString(),
                    zikr = bindingAddDialog.etEnterRussianWord.text.toString()
                );
                viewModel.addZikr(zikrInfo)
                mAlertDialog.dismiss()
            }

            bindingAddDialog.btnBackAlertDialog.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }
}