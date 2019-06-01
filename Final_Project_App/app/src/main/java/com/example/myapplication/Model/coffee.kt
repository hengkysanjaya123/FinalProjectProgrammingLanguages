package com.example.myapplication.Model

import java.io.Serializable

data class Coffee(
    var id: Int = 0,
    var title: String,
    var description: String,
    var url: String,
    var price: Int = 0
) : Serializable {}
