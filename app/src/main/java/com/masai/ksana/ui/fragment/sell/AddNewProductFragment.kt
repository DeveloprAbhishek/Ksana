package com.masai.ksana.ui.fragment.sell

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
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
import androidx.core.content.FileProvider
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.io.File
import android.provider.MediaStore.Images
import androidx.core.net.toUri


class AddNewProductFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference


    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri

    private lateinit var photoFile: File
    private lateinit var productId: String

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


            /* val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
             //imageUri = Uri.parse(photoFile.absolutePath)
             if (this.activity?.let { it1 -> takePictureIntent.resolveActivity(it1.packageManager) } != null)
                 startActivityForResult(takePictureIntent, Companion.REQUEST_CODE)
             else
                 Toast.makeText(context, "Unable to open camera", Toast.LENGTH_SHORT).show()*/


            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)
            //imageUri = Uri.parse(photoFile.absolutePath)

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

        /* if (requestCode == Companion.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
             val takenImage = data?.extras?.get("data") as Bitmap
             btnUpload.setImageBitmap(takenImage)
             /*val bytes = ByteArrayOutputStream()
             takenImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
             val path: String =
                 Images.Media.insertImage(context?.getContentResolver(), takenImage, "Title", null)
             imageUri = Uri.parse(path)*/
         } else
             super.onActivityResult(requestCode, resultCode, data)
     }*/

        if (requestCode == Companion.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)

            //imageUri = Uri.parse(photoFile.absolutePath)

            ivUploadImage.setImageBitmap(takenImage)
        } else
            super.onActivityResult(requestCode, resultCode, data)

        imageUri = photoFile.toUri()

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
                if (it.isSuccessful) {
                    uploadProductImage(productId)
                } else {
                    Toast.makeText(context, "Failed to upload Product", Toast.LENGTH_SHORT)
                        .show()
                }
                Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadProductImage(id: String) {
        //imageUri = Uri.parse(btnUpload.)
        storageReference = FirebaseStorage.getInstance().getReference("Products/" + id+".jpg")
        storageReference.putFile(imageUri).addOnSuccessListener {
            Toast.makeText(context, "Image Added Successfully", Toast.LENGTH_SHORT).show()
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

storage rule
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /{allPaths=**} {
      allow read, write: if request.auth != null;
    }
  }
}


realtime rule
{
  "rules": {
    ".read": "now < 1639161000000",  // 2021-12-11
    ".write": "now < 1639161000000",  // 2021-12-11
  }
}




<provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="com.masai.ksana.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/fileprovider" />
        </provider>
 */