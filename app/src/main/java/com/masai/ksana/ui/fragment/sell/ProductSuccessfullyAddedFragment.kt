package com.masai.ksana.ui.fragment.sell

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.masai.ksana.R
import com.masai.ksana.ui.activity.SellActivity
import kotlinx.android.synthetic.main.fragment_product_successfully_added.*

class ProductSuccessfullyAddedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_successfully_added, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDone.setOnClickListener {
            activity?.let {
                val intent = Intent(it, SellActivity::class.java)
                it.startActivity(intent)
            }
        }

        ivHome.setOnClickListener {
            activity?.let {
                val intent = Intent(it, SellActivity::class.java)
                it.startActivity(intent)
            }
        }

    }

}