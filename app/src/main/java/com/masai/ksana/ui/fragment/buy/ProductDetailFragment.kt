package com.masai.ksana.ui.fragment.buy

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_product_detail.*
import java.io.File

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var storageReference: StorageReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnUnit6m.setOnClickListener {
            btnUnit6m.setBackgroundColor(Color.DKGRAY)
        }
        btnUnit9m.setOnClickListener {
            btnUnit9m.setBackgroundColor(Color.DKGRAY)
        }
        btnUnit12m.setOnClickListener {
            btnUnit12m.setBackgroundColor(Color.DKGRAY)
        }


        btnDiameter6mm.setOnClickListener {
            btnDiameter6mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter8mm.setOnClickListener {
            btnDiameter8mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter10mm.setOnClickListener {
            btnDiameter10mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter12mm.setOnClickListener {
            btnDiameter12mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter16mm.setOnClickListener {
            btnDiameter16mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter20mm.setOnClickListener {
            btnDiameter20mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter25mm.setOnClickListener {
            btnDiameter25mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter28mm.setOnClickListener {
            btnDiameter28mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter30mm.setOnClickListener {
            btnDiameter30mm.setBackgroundColor(Color.DKGRAY)
        }
        btnDiameter32mm.setOnClickListener {
            btnDiameter32mm.setBackgroundColor(Color.DKGRAY)
        }

        //setting product details
        var args = arguments
        var title =
            "" + args?.get("productName") + " " + args?.get("materialGrade") + ""
        tvProductName.text = "" + title
        tvPrice.text = "₹" + args?.get("productPrice")
        var id = "" + args?.get("id")

        //setting product image in detailed view
        storageReference =
            FirebaseStorage.getInstance().reference.child("Products/" + id + ".jpg")
        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            ivDetailProductImage.setImageBitmap(bitmap)
        }

        ivProductDetailBuy.setOnClickListener {

            if (args != null) {
                parentFragmentManager.setFragmentResult("product1", args)
            }
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                AddDeliveryAddressFragment(),
                "Product Detail Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()

        }
    }

}