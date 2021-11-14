package com.masai.ksana.ui.fragment.sell

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_add_new_product.*
import kotlinx.android.synthetic.main.fragment_sell_select_credit.*
import kotlinx.android.synthetic.main.fragment_sell_select_credit.btnProceed

class SellSelectCreditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell_select_credit, container, false)
    }

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
        var address: String = ""
        var deliveryDate: String = ""
        var creditType: String = ""

        parentFragmentManager.setFragmentResultListener(
            "sellProduct2",
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
                address = bundle.getString("address").toString()
                deliveryDate = bundle.getString("deliveryDate").toString()
            })

        btnProceed.setOnClickListener {

            if (etBankAccountNo.text.toString() != "" && etIFSCCode.text.toString() != "")
                creditType = "Bank Account Payment"
            else if (etUpi.text.toString() != null)
                creditType = "Upi Payment"
            else {
                etBankAccountNo.error = "Please Enter Bank Account Number"
                etIFSCCode.error = "Please Enter IFSC Code"
                etIFSCCode.error = "Please Enter UPI ID"
                return@setOnClickListener
            }
            val bundle = Bundle()
            bundle.putString("diameter", diameter)
            bundle.putString("id", id)
            bundle.putString("materialGrade", materialGrade)
            bundle.putString("productName", productName)
            bundle.putString("productPrice", productPrice)
            bundle.putString("productType", productType)
            bundle.putString("quantity", quantity)
            bundle.putString("unitLength", unitLength)
            bundle.putString("address", address)
            bundle.putString("deliveryDate", deliveryDate)
            bundle.putString("creditType", creditType)

            parentFragmentManager.setFragmentResult("sellProduct3", bundle)
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                ProductSuccessfullyAddedFragment(),
                "Product Successfully Added Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }

    }

}