package com.masai.ksana.ui.fragment.sell

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_sell_shipment_and_payment.*
import kotlinx.android.synthetic.main.fragment_sell_shipment_and_payment.btnProceed
import kotlinx.android.synthetic.main.fragment_sell_shipment_and_payment.etState

class SellShipmentAndPaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell_shipment_and_payment, container, false)
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

        parentFragmentManager.setFragmentResultListener(
            "sellProduct1",
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
                "" + etCompanyName.text.toString() + ", " + etAddress.text.toString() + ", " + etState.text.toString() + ". PIN-" + etState.text.toString() + ""
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
            bundle.putString("deliveryDate", etSetShipmentDeliveryDate.text.toString())
            parentFragmentManager.setFragmentResult("sellProduct2", bundle)
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                SellSelectCreditFragment(),
                "Sell Select Credit Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }

    }

}

/*

    <LinearLayout
        android:id="@+id/llSetProductPrice"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="20dp"
        android:layout_marginRight="20dp"
        android:orientation="horizontal"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/llSetShipmentDeliveryDate">

        <TextView
            android:id="@+id/tvSetProductPrice"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:fontFamily="@font/nunito_regular"
            android:padding="10dp"
            android:text="Set Product Price"
            android:textColorHint="@color/p_white_900"
            android:textSize="16sp" />

        <EditText
            android:id="@+id/etSetProductPrice"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="130dp"
            android:hint="XXXXXXX" />

    </LinearLayout>
 */