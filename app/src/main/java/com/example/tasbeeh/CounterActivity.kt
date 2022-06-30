package com.example.tasbeeh

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
        }


        var clickedTimes = 0
        binding.btnForTap.setOnClickListener {
                    clickedTimes++

                    binding.counter.text = clickedTimes.toString()

                    binding.fluctuatedCircleUp.isVisible = false
                    binding.fluctuatedCircleDown.isVisible = true

            val run = Runnable {
                binding.fluctuatedCircleDown.isVisible = false
                binding.fluctuatedCircleUp.isVisible = true
            }
            val handler = Handler()
            handler.postDelayed(run, 200)


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