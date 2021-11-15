package com.masai.ksana.ui.fragment.buy

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
import com.masai.ksana.data.OrdersProductList
import com.masai.ksana.ui.activity.HomeActivity
import com.masai.ksana.ui.adapter.CartProductAdapter
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivHome.setOnClickListener {
            activity?.let {
                val intent = Intent(it, HomeActivity::class.java)
                it.startActivity(intent)
            }

        }

        //getting products from firebase database
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("orders")
        getProducts()
    }

    override fun onResume() {
        super.onResume()
        //getProducts()
    }

    private fun getProducts() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<OrdersProductList>()
                for (data in snapshot.children) {
                    var model = data.getValue(OrdersProductList::class.java)
                    list.add(model as OrdersProductList)
                }
                if (list.size > 0) {
                    var adapter = CartProductAdapter(list)
                    recyclerViewCart.adapter = adapter
                    recyclerViewCart.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }
        })
    }


}