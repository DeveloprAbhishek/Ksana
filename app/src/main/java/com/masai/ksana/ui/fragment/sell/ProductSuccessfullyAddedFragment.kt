package com.masai.ksana.ui.fragment.sell

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import com.masai.ksana.R
import com.masai.ksana.ui.activity.SellActivity
import kotlinx.android.synthetic.main.fragment_product_successfully_added.*
import kotlinx.android.synthetic.main.fragment_product_successfully_added.ivHome
import kotlinx.android.synthetic.main.fragment_sell_select_credit.*

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

        parentFragmentManager.setFragmentResultListener(
            "sellProduct3",
            this,
            FragmentResultListener() { s: String, bundle: Bundle ->

                var productTitle =
                    "" + bundle.getString("productName") + " " + bundle.getString("productType") + ""

                tvProductTitle.text = productTitle
                tvUnitLength.text = bundle.getString("unitLength") + "m"
                tvDiameter.text = bundle.getString("diameter") + "mm"
                tvQuantity.text = bundle.getString("quantity") + " TON"
                tvFacilityAddress.text = bundle.getString("address")
                tvCreditMethod.text = bundle.getString("creditType")

                tvDeliveryToFacility.text = bundle.getString("deliveryDate")

                tvProductPrice.text = "₹" + bundle.getString("productPrice")
                //tvTransactionID.text = bundle.getString("id")
            })

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