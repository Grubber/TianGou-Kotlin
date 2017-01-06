package com.github.grubber.tiangou_kotlin.util

import android.content.Context
import android.widget.Toast

/**
 * Created by grubber on 2017/1/6.
 */
class ToastHelper(private val context: Context) {
    fun show(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}