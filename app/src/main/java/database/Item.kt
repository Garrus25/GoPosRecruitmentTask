package database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Item(
    @Id var id: Long = 0,
    var price: Int = 0,
    var name: String = "",
    var tax_id: Int = 0,
    var category_id: String = "",
    var image_link: String = "",
)
