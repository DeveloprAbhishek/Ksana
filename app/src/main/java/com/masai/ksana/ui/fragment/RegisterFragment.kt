package com.masai.ksana.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnProceed.setOnClickListener {
            nextFragment()
        }

    }

    private fun nextFragment() {
        val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
        ft.replace(
            R.id.containerSignUp,
            BuySellFragment(),
            "Buy Sell Fragment"
        )
        ft.addToBackStack(null)
        ft.commit()
    }

}