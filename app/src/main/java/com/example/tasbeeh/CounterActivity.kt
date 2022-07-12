package com.example.tasbeeh

import android.content.Context
import android.content.Intent
import android.os.*
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.O)
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

            //postDelay
            val run = Runnable {
                binding.fluctuatedCircleDown.isVisible = false
                binding.fluctuatedCircleUp.isVisible = true
            }
            val handler = Handler()
            handler.postDelayed(run, 100)


            //vibrate
            if (clickedTimes % 100 == 0 || clickedTimes == 33) {
                val vibrate = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) {
                    vibrate.vibrate(
                        VibrationEffect.createOneShot(
                            1000,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    vibrate.vibrate(1000)
                }
            }
            //sharPref
            saveData()
        }
        binding.btnRefreshInsider.setOnClickListener {
            clickedTimes = 0
            binding.counter.text = clickedTimes.toString()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        //sharedPreference
        loadData()
    }


    private fun saveData() {
        val insertedText = binding.counter.text.toString()
        binding.counter.text = insertedText

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("STRING_KEY", insertedText)
        }.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)

        binding.counter.text = savedString
    }
}