package com.masai.ksana.ui.fragment.sell

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.masai.ksana.R
import com.masai.ksana.ui.activity.HomeActivity
import com.masai.ksana.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_sell_menu.*
import kotlinx.android.synthetic.main.fragment_sell_menu.ivLogOut
import kotlinx.android.synthetic.main.fragment_sell_menu.ivProductImage
import kotlinx.android.synthetic.main.fragment_sell_menu.tvProfileMobileNumber
import kotlinx.android.synthetic.main.fragment_sell_menu.tvProfileName

class SellMenuFragment : Fragment() {

    //profile
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoToBuy.setOnClickListener {
            activity?.let {
                val intent = Intent(it, HomeActivity::class.java)
                it.startActivity(intent)
            }
        }

        ivProfile.setOnClickListener {
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                SellProfileFragment(),
                "Sell Profile Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }

        ivInventory.setOnClickListener {
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                SellCartFragment(),
                "Sell Cart Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }

        //profile
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        if (user != null) {
            Glide.with(ivProductImage).load(user.photoUrl).into(ivProductImage)
            tvProfileName.text = user.displayName
            tvProfileMobileNumber.text = user.phoneNumber
        }

        ivLogOut.setOnClickListener {
            mAuth.signOut()
            val logout = Intent(activity, LoginActivity::class.java)
            startActivity(logout)
            (activity as Activity?)?.overridePendingTransition(0, 0)
        }
    }

}