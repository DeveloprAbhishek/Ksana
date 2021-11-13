package com.masai.ksana.ui.fragment.buy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_add_delivery_address.*
import kotlinx.android.synthetic.main.fragment_select_address.*
import kotlinx.android.synthetic.main.fragment_select_address.btnProceed

class SelectAddressFragment : Fragment(R.layout.fragment_select_address) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(
            "product2",
            this,
            FragmentResultListener() { s: String, bundle: Bundle ->
                tvName.text = bundle.getString("firstName") + " " + bundle.getString("lastName")
                tvMobileNumber.text = bundle.getString("mobileNumber")
                tvAddress.text = bundle.getString("address")
            })

        btnProceed.setOnClickListener {
            /*var address: String =
                "Qtr-" + etFlatNo.text.toString() + ", " + etSociety.text.toString() + ", " + etState.text.toString() + ". PIN-" + etPin.text.toString() + ""
            val bundle = Bundle()
            bundle.putString("diameter", diameter)
            bundle.putString("id", id)
            bundle.putString("materialGrade", materialGrade)
            bundle.putString("productName", productName)
            bundle.putString("productPrice", productPrice)
            bundle.putString("productType", productType)
            bundle.putString("quantity", quantity)
            bundle.putString("unitLength", unitLength)
            bundle.putString("firstName", etFirstName.text.toString())
            bundle.putString("lastName", etLastName.text.toString())
            bundle.putString("mobileNumber", etMobile.text.toString())
            bundle.putString("address", address)
            parentFragmentManager.setFragmentResult("product2", bundle)*/
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                SelectPaymentFragment(),
                "Select Payment Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }

    }

}