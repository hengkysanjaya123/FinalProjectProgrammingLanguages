package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.example.myapplication.Helper.core
import com.example.myapplication.Http.doAsync
import com.example.myapplication.Model.Coffee
import kotlinx.android.synthetic.main.activity_add_coffee.*
import org.json.JSONObject

class AddCoffeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coffee)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val coffeeList = intent.getSerializableExtra("coffeeList") as ArrayList<Coffee>

        var id = 1
        var max = coffeeList.maxBy { t -> t.id }
        if (max != null) {
            id = max?.id + 1
        }

        btnAdd.setOnClickListener {
            if (etTitle.text.isEmpty()) etTitle.error = "Title must be filled"
            if (etDescription.text.isEmpty()) etDescription.error = "Description must be filled"
            if (etPrice.text.isEmpty()) etPrice.error = "Price must be filled"
            if (etImageUrl.text.isEmpty()) etImageUrl.error = "Image url must be filled"


            val obj = JSONObject()
            obj.put("id", id)
            obj.put("title", etTitle.text)
            obj.put("description", etDescription.text)
            obj.put("url", etImageUrl.text)
            obj.put("price", etPrice.text)

            doAsync("${core.BASE_URL}/coffees", "POST", obj.toString(), "") {
                core.message(this, it)
                this.finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                if (!etTitle.text.isEmpty() || !etDescription.text.isEmpty() || !etPrice.text.isEmpty() || !etImageUrl.text.isEmpty()) {
                    val alert = AlertDialog.Builder(this)
                    alert.setTitle("Confirmation")
                    alert.setMessage("Do you want to discard changes ?")
                    alert.setPositiveButton("Yes") { dialog, which ->
                        this.finish()
                    }
                    alert.setNegativeButton("No") { dialog, which ->

                    }
                    alert.show()
                } else {
                    this.finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
