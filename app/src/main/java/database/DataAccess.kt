package database

import api.Product
import io.objectbox.Box

class DataAccess{
    val itemsBox = ObjectBox.store.boxFor(Item::class.java)

    fun insertData(item: ArrayList<Product>) {

        var link = ""

        item.forEach {
            if (it.image == null) {
                link = ""
            } else {
                link = it.image.default_link
            }
            itemsBox.put(Item(it.id.toLong(),
                it.price.amount,
                it.name,
                it.tax_id,
                it.category_id,
                link))
        }
    }

    fun getData(): Box<Item>? {
        return itemsBox
    }
}