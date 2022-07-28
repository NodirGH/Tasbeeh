package com.example.tasbeeh.ui.counter

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
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

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private lateinit var bindingDialogSetting: DialogSettingBinding
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

        var clickedTimes = 0
        binding.btnForTap.setOnClickListener {
            clickedTimes++

            binding.tvCounter.text = clickedTimes.toString()

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrate.vibrate(
                        VibrationEffect.createOneShot(
                            1000,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                }
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

        val mp = MediaPlayer.create(this, R.raw.subhanolloh)
        binding.btnPlay.setOnClickListener {
            mp.start()
        }

        binding.btnSettings.setOnClickListener {
            bindingDialogSetting =
                DialogSettingBinding.inflate(LayoutInflater.from(this), binding.root, false)
            val builder = AlertDialog.Builder(this)
            builder.setView(bindingDialogSetting.root)
            builder.create()
            val alertDialog = builder.show()

        bindingDialogSetting.ivYellowTasbeehSetting.setOnClickListener {
            binding.cirleOneUp.setImageResource(R.drawable.single_yellow_stone)
            binding.circleTwoUp.setImageResource(R.drawable.single_yellow_stone)
            binding.circleThreeUp.setImageResource(R.drawable.single_yellow_stone)
            binding.fluctuatedCircleUp.setImageResource(R.drawable.single_yellow_stone)
            binding.fluctuatedCircleDown.setImageResource(R.drawable.single_yellow_stone)
            binding.circleDown.setImageResource(R.drawable.single_yellow_stone)
            alertDialog.dismiss()
        }

            bindingDialogSetting.ivRedTasbeehSetting.setOnClickListener {
                binding.cirleOneUp.setImageResource(R.drawable.single_red_stone)
                binding.circleTwoUp.setImageResource(R.drawable.single_red_stone)
                binding.circleThreeUp.setImageResource(R.drawable.single_red_stone)
                binding.fluctuatedCircleUp.setImageResource(R.drawable.single_red_stone)
                binding.fluctuatedCircleDown.setImageResource(R.drawable.single_red_stone)
                binding.circleDown.setImageResource(R.drawable.single_red_stone)
                alertDialog.dismiss()
            }

            bindingDialogSetting.ivGreenTasbeehSetting.setOnClickListener {
                binding.cirleOneUp.setImageResource(R.drawable.single_green_stone)
                binding.circleTwoUp.setImageResource(R.drawable.single_green_stone)
                binding.circleThreeUp.setImageResource(R.drawable.single_green_stone)
                binding.fluctuatedCircleUp.setImageResource(R.drawable.single_green_stone)
                binding.fluctuatedCircleDown.setImageResource(R.drawable.single_green_stone)
                binding.circleDown.setImageResource(R.drawable.single_green_stone)
                alertDialog.dismiss()
            }
        }
    }

    override fun onPause() {
        if (zikr != null)
            viewModel.updateCount(zikr!!.id, binding.tvCounter.text.toString().toInt())
        super.onPause()
    }


}