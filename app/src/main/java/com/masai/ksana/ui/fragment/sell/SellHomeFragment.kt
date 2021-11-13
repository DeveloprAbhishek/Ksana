package com.masai.ksana.ui.fragment.sell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.masai.ksana.R
import com.masai.ksana.ui.buy_adapters.ProductAdapter
import kotlinx.android.synthetic.main.fragment_sell_home.*

class SellHomeFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNew.setOnClickListener {
            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            ft.replace(
                R.id.framelayout_container,
                SellCartFragment(),
                "Sell Cart Fragment"
            )
            ft.addToBackStack(null)
            ft.commit()
        }


        database = FirebaseDatabase.getInstance()
        reference = database.getReference("products")
        getProducts()
    }

    private fun getProducts() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list1 = ArrayList<SellProductList>()
                val list2 = ArrayList<SellProductList>()
                for (data in snapshot.children) {
                    var materialGrade = data.getValue(SellProductList::class.java)?.materialGrade
                    if (materialGrade == "Fe-500") {
                        var model1 = data.getValue(SellProductList::class.java)
                        list1.add(model1 as SellProductList)
                    } else if (materialGrade == "Fe-550") {
                        var model2 = data.getValue(SellProductList::class.java)
                        list2.add(model2 as SellProductList)
                    }
                }
                if (list1.size > 0 || list2.size > 0) {
                    var adapter = ProductAdapter(list1)
                    Fe500TMTBarSellHomeRecyclerView.adapter = adapter
                    Fe500TMTBarSellHomeRecyclerView.layoutManager = GridLayoutManager(context, 2)
                    var adapter2 = ProductAdapter(list2)
                    Fe550TMTBarSellHomeRecyclerView.adapter = adapter2
                    Fe550TMTBarSellHomeRecyclerView.layoutManager = GridLayoutManager(context, 2)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (fragment in childFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}