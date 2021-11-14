package com.masai.ksana.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.BuySellFragment
import com.masai.ksana.ui.fragment.splashscreen.SplashFragment1

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /*Handler().postDelayed({



            finish()
        }, 2000)*/

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is signed in
            setCurrentFragment(BuySellFragment())
            /*val i = Intent(this@SplashActivity, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)*/
        } else {
            // User is signed out
            setCurrentFragment(SplashFragment1())
            /*val i = Intent(this@SplashActivity, SignUpActivity::class.java)
            startActivity(i)
            Log.d("SIGNOUT", "onAuthStateChanged:signed_out")*/
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framelayout_container, fragment)
                .commit()
        }
}