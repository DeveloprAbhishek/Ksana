package com.masai.ksana.ui.fragment.buy

import com.masai.ksana.ui.fragment.sell.SellProductList

interface ProductClicked {

    fun productItemClicked(position: Int, sellProductList: SellProductList)

}