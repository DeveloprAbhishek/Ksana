package com.masai.ksana.ui.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.masai.ksana.R
import com.masai.ksana.data.SellProductList
import com.masai.ksana.ui.inter_face.ProductClicked
import kotlinx.android.synthetic.main.product_item_view.view.*
import java.io.File

class ProductAdapter(
    var list: ArrayList<SellProductList>,
    val itemClickListener: ProductClicked
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    //Product ViewHolder
    class ProductViewHolder(
        private val view: View,
        val clickListener: ProductClicked
    ) : RecyclerView.ViewHolder(view) {
        internal fun setData(products: SellProductList) {

            lateinit var storageReference: StorageReference
            view.apply {

                //talking first word of product name
                var name = products.productName
                name = name!!.takeWhile { !it.isWhitespace() }

                //setting product title in buy home layout recycler view
                var title: String =
                    "" + name + " " + products.productType + " " + products.materialGrade
                tvProductTitle.text = title

                //setting product price in buy home layout recycler view
                var price: String = "₹" + products.productPrice
                tvProductPrice.text = price

                //setting product image in buy home layout recycler view
                storageReference =
                    FirebaseStorage.getInstance().reference.child("Products/" + products.id + ".jpg")
                val localFile = File.createTempFile("tempImage", "jpg")
                storageReference.getFile(localFile).addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    ivProductImage.setImageBitmap(bitmap)
                }
            }
            view.setOnClickListener {
                clickListener.productItemClicked(adapterPosition, products)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_view, parent, false)
        return ProductViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val workoutList = list[position]
        holder.setData(workoutList)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}