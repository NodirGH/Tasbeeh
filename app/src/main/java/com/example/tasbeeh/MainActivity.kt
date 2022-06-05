package com.example.tasbeeh

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasbeeh.data.ZikrData
import com.example.tasbeeh.data.ZikrInfo
import com.example.tasbeeh.data.local.ZikrLocal
import com.example.tasbeeh.data.mapper.ZikrMapper
import com.example.tasbeeh.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var zikrAdapter: ZikrAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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



    }

}