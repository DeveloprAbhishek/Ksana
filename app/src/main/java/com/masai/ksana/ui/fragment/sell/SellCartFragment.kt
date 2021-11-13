package com.masai.ksana.ui.fragment.sell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.masai.ksana.R
import com.masai.ksana.data.SellProductList
import com.masai.ksana.ui.activity.AddNewProductActivity
import com.masai.ksana.ui.adapter.SellCartProductAdapter
import kotlinx.android.synthetic.main.fragment_sell_cart.*

class SellCartFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPersonFab.setOnClickListener {
            val intent = Intent(context, AddNewProductActivity::class.java)
            startActivity(intent)

        }

        //getting products from firebase database
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("products")
        getProducts()
    }

    private fun getProducts() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<SellProductList>()
                for (data in snapshot.children) {
                    var model = data.getValue(SellProductList::class.java)
                    list.add(model as SellProductList)
                }
                if (list.size > 0) {
                    var adapter = SellCartProductAdapter(list)
                    recyclerViewSellCart.adapter = adapter
                    recyclerViewSellCart.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }
        })
    }

    //passing photo file to fragment
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (fragment in childFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}