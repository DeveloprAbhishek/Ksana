package com.masai.ksana.ui.inter_face

import com.masai.ksana.data.SellProductList

interface ProductClicked {

    fun productItemClicked(position: Int, sellProductList: SellProductList)

}