package com.masai.ksana.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.buy.ProductDetailFragment

class DetailedProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_product)

        val productDetailFragment = ProductDetailFragment()
        var bundle: Bundle? = intent.getBundleExtra("product")
        productDetailFragment.arguments = bundle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framelayout_container, productDetailFragment)
                .commit()
        }

    }

}