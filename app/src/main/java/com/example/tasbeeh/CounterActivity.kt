package com.example.tasbeeh

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasbeeh.data.ZikrInfo
import com.example.tasbeeh.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding

    companion object {
        val ZIKR_INFO = ""
        fun startFromMainActivity(activity: AppCompatActivity, zikr: ZikrInfo) {
            val intent = Intent(activity, CounterActivity::class.java)
            intent.putExtra(ZIKR_INFO, zikr)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val zikr = intent.getParcelableExtra<ZikrInfo>(ZIKR_INFO)

        zikr?.let {
            binding.tvTasbehWordInside.text = zikr.zikr
            binding.ivInside.setImageResource(R.drawable.heaven)
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

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}