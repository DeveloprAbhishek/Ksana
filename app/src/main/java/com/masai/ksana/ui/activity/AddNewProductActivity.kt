package com.masai.ksana.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.sell.AddNewProductFragment

class AddNewProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_product)

        val addNewProductFragment = AddNewProductFragment()
        //var bundle: Bundle? = intent.getBundleExtra("product")
        //addNewProductFragment.arguments = bundle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framelayout_container, addNewProductFragment)
                .commit()
        }

    }
}