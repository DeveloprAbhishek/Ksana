package com.masai.ksana.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.splashscreen.SplashFragment1

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setCurrentFragment(SplashFragment1())


        /*
        //dummy
        btnSell.setOnClickListener {
            val i = Intent(this@LoginActivity, SellActivity::class.java)
            startActivity(i)
        }
        btnBuy.setOnClickListener {
            val i = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(i)
        }*/

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framelayout_container, fragment)
                .commit()
        }
}