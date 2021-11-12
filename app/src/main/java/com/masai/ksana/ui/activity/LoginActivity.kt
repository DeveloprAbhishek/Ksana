package com.masai.ksana.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masai.ksana.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //dummy
        btnSell.setOnClickListener {
            val i = Intent(this@LoginActivity, SellActivity::class.java)
            startActivity(i)
        }
        btnBuy.setOnClickListener {
            val i = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(i)
        }
    }
}