package com.masai.ksana.ui.fragment.sell

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.masai.ksana.R
import kotlinx.android.synthetic.main.fragment_add_new_product.*
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.FileProvider
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import androidx.core.net.toUri
import androidx.fragment.app.FragmentTransaction
import com.masai.ksana.data.SellProductList
import kotlinx.android.synthetic.main.fragment_add_new_product.btnProceed

class AddNewProductFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
    private lateinit var photoFile: File

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

    var productName = ""
    var productPrice = ""
    var productType = ""
    var materialGrade = ""
    var unitLength = ""
    var diameter = ""
    var quantity = ""
    var productId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //save product data
        btnProceed.setOnClickListener {

            saveProduct()

/*
            val bundle = Bundle()
            bundle.putString("diameter", etEnterDiameter.text.toString())
            bundle.putString("id", productId)
            bundle.putString("materialGrade", etEnterMaterialGrade.text.toString())
            bundle.putString("productName", etEnterProductName.text.toString())
            bundle.putString("productPrice", etEnterProductPrice.text.toString())
            //product type radio button
            radioGroup = view.findViewById(R.id.groupRadio)
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            radioButton = view.findViewById(intSelectButton)!!
            productType = radioButton.text.toString()
            bundle.putString("productType", productType)
            bundle.putString("quantity", etEnterQuantity.text.toString())
            bundle.putString("unitLength", etEnterUnitLength.text.toString())

            parentFragmentManager.setFragmentResult("sellProduct1", bundle)

            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                SellShipmentAndPaymentFragment(),
                "Sell Shipment And Payment Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
*/
        }

        //upload from gallery
        btnUpload.setOnClickListener {
            selectImage()
        }

        //capture and upload from camera
        btnOpenCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)
            val fileProvider =
                context?.let { it1 ->
                    FileProvider.getUriForFile(
                        it1,
                        "com.masai.ksana.fileprovider",
                        photoFile
                    )
                }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            if (this.activity?.let { it1 -> takePictureIntent.resolveActivity(it1.packageManager) } != null)
                startActivityForResult(takePictureIntent, Companion.REQUEST_CODE)
            else
                Toast.makeText(context, "Unable to open camera", Toast.LENGTH_SHORT).show()
        }
    }

    // select image for -> upload from gallery
    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)
    }

    // get photo file for -> upload from camera
    private fun getPhotoFile(fileName: String): File {
        //Use `getExternalFilesDir` on Context to access package-specific directories.
        val storageDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, "jpg", storageDirectory)
    }

    // get result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //get uri for -> upload from camera
        if (requestCode == Companion.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            ivUploadImage.setImageBitmap(takenImage)
            imageUri = photoFile.toUri()
        }
        //get uri for -> upload from gallery
        else if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data!!
            ivUploadImage.setImageURI(imageUri)
        }
        //get uri -> failed
        else
            super.onActivityResult(requestCode, resultCode, data)
    }

    //save product data to data class
    private fun saveProduct() {
        productName = etEnterProductName.text.toString().trim()
        if (productName.isEmpty()) {
            etEnterProductName.error = "Please Enter Product Name"
            return
        }
        productPrice = etEnterProductPrice.text.toString().trim()
        if (productPrice.isEmpty()) {
            etEnterProductPrice.error = "Please Enter Product Price"
            return
        }

        //product type radio button
        radioGroup = view?.findViewById(R.id.groupRadio)
        val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
        radioButton = view?.findViewById(intSelectButton)!!
        productType = radioButton.text.toString()

        materialGrade = etEnterMaterialGrade.text.toString().trim()
        if (materialGrade.isEmpty()) {
            etEnterMaterialGrade.error = "Please Enter Material Grade"
            return
        }
        unitLength = etEnterUnitLength.text.toString().trim()
        if (unitLength.isEmpty()) {
            etEnterUnitLength.error = "Please Enter Unit Length"
            return
        }
        diameter = etEnterDiameter.text.toString().trim()
        if (diameter.isEmpty()) {
            etEnterDiameter.error = "Please Enter Diameter"
            return
        }
        quantity = etEnterQuantity.text.toString().trim()
        if (quantity.isEmpty()) {
            etEnterQuantity.error = "Please Enter Quantity"
            return
        }

        //upload product data to realtime database
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("newProducts")
        productId = reference.push().key!!

        val product =
            productId?.let {
                SellProductList(
                    diameter,
                    it,
                    materialGrade,
                    productName,
                    productPrice,
                    productType,
                    quantity,
                    unitLength
                )
            }
        if (productId != null) {
            reference.child(productId).setValue(product).addOnCompleteListener {
                if (it.isSuccessful) {
                    uploadProductImage(productId)
                } else {
                    Toast.makeText(context, "Failed to upload Product", Toast.LENGTH_SHORT)
                        .show()
                }
                //Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //upload image to firebase storage
    private fun uploadProductImage(id: String) {
        storageReference = FirebaseStorage.getInstance().getReference("Products/" + id + ".jpg")
        storageReference.putFile(imageUri).addOnSuccessListener {
            Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show()

            val bundle = Bundle()/*
            bundle.putString("diameter", etEnterDiameter.text.toString())
            bundle.putString("id", productId)
            bundle.putString("materialGrade", etEnterMaterialGrade.text.toString())
            bundle.putString("productName", etEnterProductName.text.toString())
            bundle.putString("productPrice", etEnterProductPrice.text.toString())
            //product type radio button
            radioGroup = view?.findViewById(R.id.groupRadio)
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            radioButton = view?.findViewById(intSelectButton)!!
            productType = radioButton.text.toString()
            bundle.putString("productType", productType)
            bundle.putString("quantity", etEnterQuantity.text.toString())
            bundle.putString("unitLength", etEnterUnitLength.text.toString())*/
            bundle.putString("diameter", diameter)
            bundle.putString("id", productId)
            bundle.putString("materialGrade", materialGrade)
            bundle.putString("productName", productName)
            bundle.putString("productPrice", productPrice)
            productType = radioButton.text.toString()
            bundle.putString("productType", productType)
            bundle.putString("quantity", quantity)
            bundle.putString("unitLength", unitLength)
            parentFragmentManager.setFragmentResult("sellProduct1", bundle)

            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                SellShipmentAndPaymentFragment(),
                "Sell Shipment And Payment Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()


        }.addOnFailureListener {
            Toast.makeText(context, "Failed to upload Product", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val REQUEST_CODE = 42
        private const val FILE_NAME = "photo"
    }

}


/*

firebase storage rule

rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /{allPaths=**} {
      allow read, write: if request.auth != null;
    }
  }
}


firebase realtime rule

{
  "rules": {
    ".read": "now < 1639161000000",  // 2021-12-11
    ".write": "now < 1639161000000",  // 2021-12-11
  }
}


 */