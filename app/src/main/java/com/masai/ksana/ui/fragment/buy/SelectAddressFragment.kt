package com.masai.ksana.ui.fragment.buy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_select_address.*
import kotlinx.android.synthetic.main.fragment_select_address.btnProceed

class SelectAddressFragment : Fragment(R.layout.fragment_select_address) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var diameter: String = ""
        var id: String = ""
        var materialGrade: String = ""
        var productName: String = ""
        var productPrice: String = ""
        var productType: String = ""
        var quantity: String = ""
        var unitLength: String = ""
        var firstName: String = ""
        var lastName: String = ""
        var mobileNumber: String = ""
        var address: String = ""

        parentFragmentManager.setFragmentResultListener(
            "product2",
            this,
            FragmentResultListener() { s: String, bundle: Bundle ->

                tvName.text = bundle.getString("firstName") + " " + bundle.getString("lastName")
                tvMobileNumber.text = bundle.getString("mobileNumber")
                tvAddress.text = bundle.getString("address")

                diameter = bundle.getString("diameter").toString()
                id = bundle.getString("id").toString()
                materialGrade = bundle.getString("materialGrade").toString()
                productName = bundle.getString("productName").toString()
                productPrice = bundle.getString("productPrice").toString()
                productType = bundle.getString("productType").toString()
                quantity = bundle.getString("quantity").toString()
                unitLength = bundle.getString("unitLength").toString()
                firstName = bundle.getString("firstName").toString()
                lastName = bundle.getString("lastName").toString()
                mobileNumber = bundle.getString("mobileNumber").toString()
                address = bundle.getString("address").toString()

            })

        btnProceed.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("diameter", diameter)
            bundle.putString("id", id)
            bundle.putString("materialGrade", materialGrade)
            bundle.putString("productName", productName)
            bundle.putString("productPrice", productPrice)
            bundle.putString("productType", productType)
            bundle.putString("quantity", quantity)
            bundle.putString("unitLength", unitLength)
            bundle.putString("firstName", firstName)
            bundle.putString("lastName", lastName)
            bundle.putString("mobileNumber", mobileNumber)
            bundle.putString("address", address)
            //bundle.putString("mobileNumber", etMobile.text.toString())
            parentFragmentManager.setFragmentResult("product3", bundle)
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