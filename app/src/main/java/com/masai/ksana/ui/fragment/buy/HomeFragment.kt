package com.masai.ksana.ui.fragment.buy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.sell.SellProductList
import kotlinx.android.synthetic.main.fragment_home.*

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
                    Fe500TMTBarRecyclerView.adapter = adapter
                    Fe500TMTBarRecyclerView.layoutManager = GridLayoutManager(context, 2)
                    var adapter2 = ProductAdapter(list2)
                    Fe550TMTBarRecyclerView.adapter = adapter2
                    Fe550TMTBarRecyclerView.layoutManager = GridLayoutManager(context, 2)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }
        })
    }

}