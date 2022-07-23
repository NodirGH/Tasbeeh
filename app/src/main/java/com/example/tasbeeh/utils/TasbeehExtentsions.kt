package com.example.tasbeeh.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, "", length).show()
}

fun View.manageVisibility(isVisible :Boolean){
    if(isVisible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}