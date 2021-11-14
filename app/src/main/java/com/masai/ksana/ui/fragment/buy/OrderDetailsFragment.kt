package com.masai.ksana.ui.fragment.buy

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.masai.ksana.R
import com.masai.ksana.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.fragment_order_details.*
import java.io.File

class OrderDetailsFragment : Fragment() {

    private lateinit var storageReference: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id: String = ""

        parentFragmentManager.setFragmentResultListener(
            "product5",
            this,
            FragmentResultListener() { s: String, bundle: Bundle ->

                tvDiameter.text = bundle.getString("diameter") + "mm"
                id = bundle.getString("id") + ""
                tvMaterialGrade.text = bundle.getString("materialGrade").toString()
                tvProductTitle.text =
                    "" + bundle.getString("productName") + " " + bundle.getString("productType") + ""
                tvProductPrice.text = "₹" + bundle.getString("productPrice")
                tvQuantity.text = bundle.getString("quantity").toString() + " TON"
                tvUnitLength.text = bundle.getString("unitLength").toString()
                tvTransactionID.text = bundle.getString("id").toString()
                tvFacilityAddress.text = bundle.getString("address").toString()
                tvCreditMethod.text = bundle.getString("paymentType").toString()
                //tvDeliveryToFacility.text = bundle.getString("").toString()

            })

        //setting product image in detailed view
        storageReference =
            FirebaseStorage.getInstance().reference.child("Products/" + id.trim() + ".jpg")
        val localFile = File.createTempFile("tempImage1", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            ivOrderProductImage.setImageBitmap(bitmap)
        }

        ivProductDetailHome.setOnClickListener {
            activity?.let {
                val intent = Intent(it, HomeActivity::class.java)
                it.startActivity(intent)
            }
        }

        ivProductDetailCart.setOnClickListener {
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                CartFragment(),
                "Cart Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }

    }
}