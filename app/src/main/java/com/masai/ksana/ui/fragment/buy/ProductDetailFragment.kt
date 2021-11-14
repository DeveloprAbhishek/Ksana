package com.masai.ksana.ui.fragment.buy

import android.graphics.BitmapFactory
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