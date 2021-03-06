package com.masai.ksana.ui.fragment.buy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.masai.ksana.R
import com.masai.ksana.ui.activity.DetailedProductActivity
import com.masai.ksana.data.SellProductList
import com.masai.ksana.ui.adapter.ProductAdapter
import com.masai.ksana.ui.inter_face.ProductClicked
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), ProductClicked {

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
        reference = database.getReference("newProducts")
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
                    var adapter = ProductAdapter(list1, this@HomeFragment)
                    Fe500TMTBarRecyclerView.adapter = adapter
                    Fe500TMTBarRecyclerView.layoutManager = GridLayoutManager(context, 2)
                    var adapter2 = ProductAdapter(list2, this@HomeFragment)
                    Fe550TMTBarRecyclerView.adapter = adapter2
                    Fe550TMTBarRecyclerView.layoutManager = GridLayoutManager(context, 2)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }
        })
    }

    override fun productItemClicked(position: Int, sellProductList: SellProductList) {
        val bundle = Bundle()
        bundle.putString("diameter", sellProductList.diameter)
        bundle.putString("id", sellProductList.id)
        bundle.putString("materialGrade", sellProductList.materialGrade)
        bundle.putString("productName", sellProductList.productName)
        bundle.putString("productPrice", sellProductList.productPrice)
        bundle.putString("productType", sellProductList.productType)
        bundle.putString("quantity", sellProductList.quantity)
        bundle.putString("unitLength", sellProductList.unitLength)
        activity?.let {
            val intent = Intent(it, DetailedProductActivity::class.java)
            intent.putExtra("product", bundle)
            it.startActivity(intent)
        }
    }

}