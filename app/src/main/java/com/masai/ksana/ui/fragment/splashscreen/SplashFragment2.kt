package com.masai.ksana.ui.fragment.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_splash2.*

class SplashFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_skip.setOnClickListener {
            nextFragment()
        }

        btn_next.setOnClickListener {
            nextFragment()
        }
    }

    private fun nextFragment() {
        val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
        ft.replace(
            R.id.framelayout_container,
            SplashFragment3(),
            "Splash Fragment 3"
        )
        ft.addToBackStack(null)
        ft.commit()
    }

}