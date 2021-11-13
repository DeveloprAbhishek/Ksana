package com.masai.ksana.ui.fragment.buy

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.sell.SellProductList
import kotlinx.android.synthetic.main.product_item_view.view.*
import java.io.File

class ProductAdapter(var list: ArrayList<SellProductList>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var storageReference: StorageReference

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var productName = itemView.tvProductTitle
        var productPrice = itemView.tvProductPrice

        var productImage = itemView.ivProductImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //talking first word of product name
        var name = list[position].productName
        name = name!!.takeWhile { !it.isWhitespace() }

        //setting product title in buy home layout recycler view
        var title: String =
            "" + name + " " + list[position].productType + " " + list[position].materialGrade
        holder.productName.text = title

        //setting product price in buy home layout recycler view
        var price: String = "₹" + list[position].productPrice
        holder.productPrice.text = price

        //setting product image in buy home layout recycler view
        storageReference =
            FirebaseStorage.getInstance().reference.child("Products/" + list[position].id + ".jpg")
        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            holder.productImage.setImageBitmap(bitmap)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}