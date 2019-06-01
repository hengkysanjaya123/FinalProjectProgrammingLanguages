package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.myapplication.Helper.core
import com.example.myapplication.Http.doAsync
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            if (etEmail.text.isEmpty()) {
                etEmail.error = "Email must be filled"
                return@setOnClickListener
            }

            if (etPassword.text.isEmpty()) {
                etPassword.error = "Password must be filled"
                return@setOnClickListener
            }

            val obj = JSONObject()
            obj.put("email", etEmail.text)
            obj.put("password", etPassword.text)

            doAsync("${core.BASE_URL}/login", "POST", obj.toString(), "", pbLoading) {
                //                core.message(this, it)
                Log.d("test", it)
                if (it.isEmpty() || !it.contains("email")) {
                    core.message(this, "Email and password incorrect")
                } else {
                    etEmail.setText("")
                    etPassword.setText("")
                    val obj = JSONObject(it)

                    val role = obj.getString("role")
                    if (role == "seller") {
                        val intt = Intent(this, SellerActivity::class.java)
                        startActivity(intt)
                    } else if (role == "customer") {
                        val intt = Intent(this, MainActivity::class.java)
                        startActivity(intt)
                    }
                }
            }
        }
    }
}
