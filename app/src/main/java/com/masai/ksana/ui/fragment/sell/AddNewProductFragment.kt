package com.masai.ksana.ui.fragment.sell

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_add_new_product.*

class AddNewProductFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnProceed.setOnClickListener {
            saveProduct()
        }
    }

    private fun saveProduct() {
        val productName = etEnterProductName.text.toString().trim()
        if (productName.isEmpty()) {
            etEnterProductName.error = "Please Enter Product Name"
            return
        }
        val materialGrade = etEnterMaterialGrade.text.toString().trim()
        if (materialGrade.isEmpty()) {
            etEnterMaterialGrade.error = "Please Enter Product Name"
            return
        }
        val unitLength = etEnterUnitLength.text.toString().trim()
        if (unitLength.isEmpty()) {
            etEnterUnitLength.error = "Please Enter Product Name"
            return
        }
        val diameter = etEnterDiameter.text.toString().trim()
        if (diameter.isEmpty()) {
            etEnterDiameter.error = "Please Enter Product Name"
            return
        }
        val quantity = etEnterQuantity.text.toString().trim()
        if (quantity.isEmpty()) {
            etEnterQuantity.error = "Please Enter Product Name"
            return
        }

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("products")
        val productId = reference.push().key


        val product =
            productId?.let {
                SellProductList(
                    diameter,
                    it,
                    materialGrade,
                    productName,
                    quantity,
                    unitLength
                )
            }
        if (productId != null) {
            reference.child(productId).setValue(product).addOnCompleteListener {
                Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


/*
{
  "rules": {
    ".read": "now < 1639161000000",  // 2021-12-11
    ".write": "now < 1639161000000",  // 2021-12-11
  }
}
 */