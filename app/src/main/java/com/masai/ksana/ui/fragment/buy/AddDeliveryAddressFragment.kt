package com.masai.ksana.ui.fragment.buy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_add_delivery_address.*

class AddDeliveryAddressFragment : Fragment(R.layout.fragment_add_delivery_address) {

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

        parentFragmentManager.setFragmentResultListener(
            "product1",
            this,
            FragmentResultListener() { s: String, bundle: Bundle ->
                diameter = bundle.getString("diameter").toString()
                id = bundle.getString("id").toString()
                materialGrade = bundle.getString("materialGrade").toString()
                productName = bundle.getString("productName").toString()
                productPrice = bundle.getString("productPrice").toString()
                productType = bundle.getString("productType").toString()
                quantity = bundle.getString("quantity").toString()
                unitLength = bundle.getString("unitLength").toString()
            })

        btnProceed.setOnClickListener {
            var address: String =
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
            parentFragmentManager.setFragmentResult("product2", bundle)
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                SelectAddressFragment(),
                "Select Address Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }

    }

}