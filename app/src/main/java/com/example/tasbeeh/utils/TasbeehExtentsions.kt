package com.example.tasbeeh.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Toast

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun View.manageVisibility(isVisible :Boolean){
    if(isVisible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

fun Context.vibratePhone(){
    @Suppress("DEPRECATION")
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