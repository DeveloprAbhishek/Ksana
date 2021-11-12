package com.masai.ksana.ui.fragment.buy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.sell.SellProductList
import kotlinx.android.synthetic.main.fragment_home.*
import java.sql.DatabaseMetaData

class HomeFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                    var adapter = ProductAdapter(list)
                    recyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }

        })


    }


}