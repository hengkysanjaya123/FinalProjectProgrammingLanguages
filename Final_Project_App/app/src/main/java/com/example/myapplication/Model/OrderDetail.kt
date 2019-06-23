package com.example.myapplication.Model

import java.io.Serializable

data class OrderDetail(var id: Int, var orderId: Int, var coffeeId: Int, var qty: Int) :
    Serializable {}