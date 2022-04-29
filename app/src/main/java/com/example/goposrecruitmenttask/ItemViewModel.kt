package com.example.goposrecruitmenttask

import ItemAdapter
import android.util.Log
import androidx.lifecycle.ViewModel
import api.ApiRequest
import database.DataAccess
import database.ItemEntity
import io.objectbox.Box

class ItemViewModel : ViewModel() {
    var dataAccess = DataAccess()
    val apiRequest: ApiRequest = ApiRequest()

    var itemBox: Box<ItemEntity>? = null

    fun start() {
        if (itemBox == null) {
            apiRequest.createApiRequest()
        }
    }

    fun fetchDataFromDB() {
        if (itemBox == null) {
            itemBox = dataAccess.getData()!!
            Log.i("ViewModel", itemBox!!.all.toString())
        }
    }

    fun populateView(adapter: ItemAdapter) {
        itemBox!!.all?.forEach {
            adapter.add(ItemEntity(it.id, it.price, it.name,it.taxRate,it.category,it.image_link))
        }
    }
}