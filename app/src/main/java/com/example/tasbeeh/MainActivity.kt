package com.example.tasbeeh

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasbeeh.data.ZikrData
import com.example.tasbeeh.data.local.ZikrLocal
import com.example.tasbeeh.data.mapper.ZikrMapper
import com.example.tasbeeh.databinding.ActivityMainBinding
import com.example.tasbeeh.databinding.AddZikrBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var zikrAdapter: ZikrAdapter
    private lateinit var bindingAddZikr: AddZikrBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingAddZikr = AddZikrBinding.inflate(layoutInflater)
        val viewSecond = bindingAddZikr.root
        val view = binding.root
        setContentView(view)

        zikrAdapter = ZikrAdapter()
        zikrAdapter.callback = {
            CounterActivity.startFromMainActivity(this, it)
        }
        ZikrLocal.getLocalDB(applicationContext).zikrDao()
            .insertAll(ZikrData.getZikrs().map { it.mapToEntity() })

        val zikrEntities = ZikrLocal.getLocalDB(applicationContext).zikrDao().getAllZikrs()
        zikrAdapter.submitList(ZikrMapper.mapEntitiesToDtos(zikrEntities))

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = zikrAdapter

        //alert dialog
        binding.llAddTasbehWord.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_zikr, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            bindingAddZikr.btnAddAlertDialog.setOnClickListener {
                mAlertDialog.dismiss()
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
            }

            bindingAddZikr.btnBackAlertDialog.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }
}