package com.masai.ksana.ui.fragment.buy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_product_detail.*

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(
            "product",
            this,
            FragmentResultListener() { s: String, bundle: Bundle ->
                tvProductName.text = bundle.getString("productName")
            })
    }
}