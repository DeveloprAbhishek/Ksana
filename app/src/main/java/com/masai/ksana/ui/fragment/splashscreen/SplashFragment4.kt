package com.masai.ksana.ui.fragment.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.R
import com.masai.ksana.ui.activity.DetailedProductActivity
import com.masai.ksana.ui.activity.LoginActivity
import com.masai.ksana.ui.fragment.WelcomeFragment
import kotlinx.android.synthetic.main.fragment_splash4.*

class SplashFragment4 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener {
            nextFragment()
        }
    }

    private fun nextFragment() {

        activity?.let {
            val intent = Intent(it, LoginActivity::class.java)
            it.startActivity(intent)
        }

        /*val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
        ft.replace(
            R.id.framelayout_container,
            WelcomeFragment(),
            "Welcome Fragment"
        )
        ft.addToBackStack(null)
        ft.commit()*/
    }

}