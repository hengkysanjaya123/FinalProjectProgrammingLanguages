package com.example.myapplication.Helper

import android.content.Context
import android.widget.Toast

class core {
    companion object {
        val BASE_URL = "http://192.168.1.3:8080"

        fun message(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}