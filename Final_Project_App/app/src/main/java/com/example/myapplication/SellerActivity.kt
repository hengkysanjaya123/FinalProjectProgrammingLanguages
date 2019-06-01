package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.Helper.core
import kotlinx.android.synthetic.main.activity_seller.*

class SellerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)

        viewpager_main.adapter = MyPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_logout -> {
                doLogout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        doLogout()
//        super.onBackPressed()
    }

    fun doLogout() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Confirmation")
        alert.setMessage("Are you sure want to logout ?")
        alert.setPositiveButton("Yes") { dialog, which ->
            core.message(this, "Thank you")
            this.finish()
        }
        alert.setNegativeButton("No") { dialog, which ->

        }
        alert.show()
    }
}
