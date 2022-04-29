package database

import android.util.Log
import api.dataClasses.Product
import io.objectbox.Box

class DataAccess {
    val itemsBox = ObjectBox.store.boxFor(ItemEntity::class.java)
    fun insertData(item: ArrayList<Product>) {
        var link = ""

        item.forEach {

            if (it.image == null){
                link = "empty"
            }else{
                link = it.image.default_link
            }

            itemsBox.put(ItemEntity(
                it.id.toLong(),
                it.price.amount,
                it.name,
                it.tax.rate,
                it.category.name,
                link))
        }
        itemsBox.all.forEach {
            Log.i("Text",it.toString())
        }
    }

    fun getData(): Box<ItemEntity>? {
        return itemsBox
    }
}