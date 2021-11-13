package com.masai.ksana.ui.fragment.sell

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.masai.ksana.R
import kotlinx.android.synthetic.main.product_item_layout_sell_cart.view.*
import kotlinx.android.synthetic.main.product_item_view.view.ivProductImage
import java.io.File

class SellCartProductAdapter(var list: ArrayList<SellProductList>) :
    RecyclerView.Adapter<SellCartProductAdapter.ViewHolder>() {

    private lateinit var storageReference: StorageReference

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var productName = itemView.tvSellCartProductTitle
        var materialGrade = itemView.tvSellCartMaterialGrade
        var unitLength = itemView.tvSellCartUnitLength
        var diameter = itemView.tvSellCartDiameter
        var quantity = itemView.tvSellCartQuantity

        var productImage = itemView.ivProductImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item_layout_sell_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //setting product details in sell cart layout recycler view
        var name = list[position].productName
        holder.productName.text = name
        var grade = "Grade - " + list[position].materialGrade
        holder.materialGrade.text = grade
        var length = "Unit Length - " + list[position].unitLength + " m"
        holder.unitLength.text = length
        var dia = "Thickness - " + list[position].diameter + " mm"
        holder.diameter.text = dia
        var quant = "Net Quantity - " + list[position].quantity + " ton"
        holder.quantity.text = quant

        //setting product image in sell cart layout recycler view
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