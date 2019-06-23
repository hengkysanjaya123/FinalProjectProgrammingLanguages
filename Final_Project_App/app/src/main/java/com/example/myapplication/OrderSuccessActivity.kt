package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.myapplication.Model.OrderDetail
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_order_success.*
import java.util.*

class OrderSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        try {
            val orderList = intent.getSerializableExtra("orderList") as ArrayList<OrderDetail>
            val totalPrice = intent.getStringExtra("totalPrice")

            var randOrderId = ""
            for (i in 0..5) {
                randOrderId += "${(1..200).random()}"
            }

            var text = "${randOrderId}#"
            for (i in orderList) {
                text += "COFFEE ID : ${i.coffeeId},QTY : ${i.qty}#"
            }


            textView9.text = "ORDER ID : #${randOrderId}"
            textView10.text = "TOTAL ${totalPrice}"

            val multiFormatWriter = MultiFormatWriter();
            try {
                val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
                val barcodeEncoder = BarcodeEncoder();
                val bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageView3.setImageBitmap(bitmap);
            } catch (e: WriterException) {
                e.printStackTrace();
            }
        } catch (ex: Exception) {
            Log.d("test", ex.toString())
        }


    }
}
