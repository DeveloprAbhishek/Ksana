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
import com.masai.ksana.ui.activity.SellActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_add_new_product.*
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.navigation.ActivityNavigatorExtras
import java.lang.NullPointerException
import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Environment
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_home.mainActivity
import kotlinx.android.synthetic.main.activity_sell.*
import okhttp3.internal.notify
import java.io.File
import java.net.URI


class AddNewProductFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var fileUri: String
    private lateinit var photoFile: File

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

        btnUpload.setOnClickListener {
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

    private fun getPhotoFile(fileName: String): File {
        //Use `getExternalFilesDir` on Context to access package-specific directories.
        val storageDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, "jpg", storageDirectory)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Companion.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            btnUpload.setImageBitmap(takenImage)
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }


    private fun saveProduct() {
        val productName = etEnterProductName.text.toString().trim()
        if (productName.isEmpty()) {
            etEnterProductName.error = "Please Enter Product Name"
            return
        }
        val materialGrade = etEnterMaterialGrade.text.toString().trim()
        if (materialGrade.isEmpty()) {
            etEnterMaterialGrade.error = "Please Enter Material Grade"
            return
        }
        val unitLength = etEnterUnitLength.text.toString().trim()
        if (unitLength.isEmpty()) {
            etEnterUnitLength.error = "Please Enter Unit Length"
            return
        }
        val diameter = etEnterDiameter.text.toString().trim()
        if (diameter.isEmpty()) {
            etEnterDiameter.error = "Please Enter Diameter"
            return
        }
        val quantity = etEnterQuantity.text.toString().trim()
        if (quantity.isEmpty()) {
            etEnterQuantity.error = "Please Enter Quantity"
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

    companion object {
        private const val REQUEST_CODE = 42
        private const val FILE_NAME = "photo.jpg"
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