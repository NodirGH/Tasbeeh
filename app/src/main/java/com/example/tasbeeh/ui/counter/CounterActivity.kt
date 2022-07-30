package com.example.tasbeeh.ui.counter

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.tasbeeh.R
import com.example.tasbeeh.databinding.ActivityCounterBinding
import com.example.tasbeeh.databinding.DialogSettingBinding
import com.example.tasbeeh.model.ZikrInfo
import com.example.tasbeeh.utils.manageVisibility
import com.example.tasbeeh.utils.vibratePhone

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private lateinit var bindingDialogSetting: DialogSettingBinding
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private val viewModel: CounterViewModel by viewModels()
    private var zikr: ZikrInfo? = null

    companion object {
        const val ZIKR_INFO = ""
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

        zikr = intent.getParcelableExtra(ZIKR_INFO)

        zikr?.let {
            binding.tvTasbehWordInside.text = it.zikr
            binding.tvCounter.text = it.counter.toString()
        }

        var clickedTimes = zikr!!.counter.toString().toInt()
        binding.btnForTap.setOnClickListener {
            clickedTimes++

            binding.tvCounter.text = clickedTimes.toString()

            binding.fluctuatedCircleUp.isVisible = false
            binding.fluctuatedCircleDown.isVisible = true

            val run = Runnable {
                binding.fluctuatedCircleDown.isVisible = false
                binding.fluctuatedCircleUp.isVisible = true
            }
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(run, 100)

            if (clickedTimes % 100 == 0 || clickedTimes == 33) {
                vibratePhone()
            }
        }
        binding.btnRefreshInsider.setOnClickListener {
            clickedTimes = 0
            binding.tvCounter.text = clickedTimes.toString()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        if (zikr!!.isDeletable) {
            binding.btnPlay.manageVisibility(false)
        }

        binding.btnPlay.setOnClickListener {
            play(zikr!!.zikrAudio)
        }

        loadDataSetting()
        binding.btnSettings.setOnClickListener {
            bindingDialogSetting =
                DialogSettingBinding.inflate(LayoutInflater.from(this), binding.root, false)
            val builder = AlertDialog.Builder(this)
            builder.setView(bindingDialogSetting.root)
            builder.create()
            val alertDialog = builder.show()

            bindingDialogSetting.ivYellowTasbeehSetting.setOnClickListener {
                val yellowStone = R.drawable.single_yellow_stone
                saveDataSetting(yellowStone)
                alertDialog.dismiss()
            }

            bindingDialogSetting.ivRedTasbeehSetting.setOnClickListener {
                val redStone = R.drawable.single_red_stone
                saveDataSetting(redStone)
                alertDialog.dismiss()
            }

            bindingDialogSetting.ivGreenTasbeehSetting.setOnClickListener {
                val greenStone = R.drawable.single_green_stone
                saveDataSetting(greenStone)
                alertDialog.dismiss()
            }
        }
    }

    private fun loadDataSetting() {
        val sharedPreferences = getSharedPreferences("TASBEH_STONES", Context.MODE_PRIVATE)
        val savedInteger = sharedPreferences.getInt("CHANGE_COLOR", 0)
        binding.cirleOneUp.setImageResource(savedInteger)
        binding.circleTwoUp.setImageResource(savedInteger)
        binding.circleThreeUp.setImageResource(savedInteger)
        binding.fluctuatedCircleUp.setImageResource(savedInteger)
        binding.fluctuatedCircleDown.setImageResource(savedInteger)
        binding.circleDown.setImageResource(savedInteger)
    }

    private fun saveDataSetting(drawable: Int) {
        val sharedPreferences = getSharedPreferences("TASBEH_STONES", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putInt("CHANGE_COLOR", drawable)
        }.apply()
        binding.cirleOneUp.setImageResource(drawable)
        binding.circleTwoUp.setImageResource(drawable)
        binding.circleThreeUp.setImageResource(drawable)
        binding.fluctuatedCircleUp.setImageResource(drawable)
        binding.fluctuatedCircleDown.setImageResource(drawable)
        binding.circleDown.setImageResource(drawable)
    }

    private fun play(asset: String) {
        try {
            val audio = applicationContext.assets.openFd(asset)
            mediaPlayer.setDataSource(audio.fileDescriptor, audio.startOffset, audio.length)
            audio.close()
            mediaPlayer.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mediaPlayer.start()
    }

    override fun onPause() {
        if (zikr != null)
            viewModel.updateCount(zikr!!.id, binding.tvCounter.text.toString().toInt())
        super.onPause()
    }
}