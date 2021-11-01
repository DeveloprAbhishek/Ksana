package com.masai.ksana.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.CartFragment
import com.masai.ksana.ui.fragment.HomeFragment
import com.masai.ksana.ui.fragment.MenuFragment
import com.masai.ksana.ui.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setCurrentFragment(HomeFragment())
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
                R.id.page_1 -> setCurrentFragment(HomeFragment())
                R.id.page_2 -> setCurrentFragment(CartFragment())
                R.id.page_3 -> setCurrentFragment(ProfileFragment())
                R.id.page_4 -> setCurrentFragment(MenuFragment())
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