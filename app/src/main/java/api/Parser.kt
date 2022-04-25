package api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import database.Item
import database.ObjectBox
private const val TAG = "Parser"

class Parser(var jsonObject: JsonObject) {
    val itemsBox = ObjectBox.store.boxFor(Item::class.java)

    fun parseData() {
        val json = jsonObject
        val gson = Gson()
        val data: Data = gson.fromJson(json, Data::class.java)

        val productList: ArrayList<Product> = ArrayList()

        for (jsonElement in data.product) {
            val newProduct = gson.fromJson(jsonElement, Product::class.java)
            productList.add(newProduct)
        }

        itemsBox.panicModeRemoveAll()


        for (elem in productList) {
            Log.i(TAG, elem.toString())
            var imageLink: String

            if (elem.image == null) {
                imageLink = "empty"
            } else {
                imageLink = elem.image.default_link
            }
            val item = Item(0, elem.price.amount, elem.name, elem.tax_id, elem.category_id, imageLink)
            itemsBox.put(item)
            Log.i(TAG, "database content ${itemsBox.all}")
        }
    }
}