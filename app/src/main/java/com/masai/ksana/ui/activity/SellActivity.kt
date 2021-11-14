package com.masai.ksana.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.sell.SellCartFragment
import com.masai.ksana.ui.fragment.sell.SellHomeFragment
import com.masai.ksana.ui.fragment.sell.SellMenuFragment
import com.masai.ksana.ui.fragment.sell.SellProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class SellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)
        setCurrentFragment(SellHomeFragment())

        /*if (intent != null) {
            val bundle = Bundle()
            bundle.putString("key", intent.getStringExtra("intent"))
            val fragment = PremiumFragment()
            fragment.arguments = bundle
            val ft = supportFragmentManager.beginTransaction()
            if (intent.getStringExtra("intent").equals("success")) {
                ft.replace(R.id.framelayout_container, fragment)
                    .commit()
            }
        }
        app:itemIconTint="@color/nav_btn_bg"
        app:itemRippleColor="@color/green"
        app:itemTextColor="@color/nav_btn_bg"

        android:background="@drawable/bottom_nav_bar_bg"
        */


        bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> setCurrentFragment(SellHomeFragment())
                R.id.page_2 -> setCurrentFragment(SellCartFragment())
                R.id.page_3 -> setCurrentFragment(SellProfileFragment())
                R.id.page_4 -> setCurrentFragment(SellMenuFragment())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framelayout_container, fragment)
                .commit()
        }
}