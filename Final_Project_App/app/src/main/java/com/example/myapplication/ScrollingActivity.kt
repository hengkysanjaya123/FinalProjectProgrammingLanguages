package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.myapplication.Model.Coffee
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import java.text.NumberFormat
import java.util.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val coffee = intent.getSerializableExtra("objCoffee") as Coffee
//        tvTitle.text = coffee.title

        this.title = coffee.title

        val formatter = NumberFormat.getNumberInstance(Locale.ENGLISH)
        tvPrice.text = "Rp "+formatter.format(coffee.price)
        tvDescription.text = coffee.description.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
