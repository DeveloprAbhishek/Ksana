package com.masai.ksana.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.masai.ksana.R
import com.masai.ksana.ui.activity.HomeActivity
import com.masai.ksana.ui.activity.SellActivity
import kotlinx.android.synthetic.main.fragment_buy_sell.*

class BuySellFragment : Fragment(R.layout.fragment_buy_sell) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_Sell.setOnClickListener {
            val i = Intent(context, SellActivity::class.java)
            startActivity(i)
        }

        btn_Buy.setOnClickListener {
            val i = Intent(context, HomeActivity::class.java)
            startActivity(i)
        }

    }

}