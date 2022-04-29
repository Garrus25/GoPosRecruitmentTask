package database

import androidx.annotation.Nullable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ItemEntity(
    @Id(assignable = true) var id: Long = 0,
    var price: Int = 0,
    var name: String = "",
    var taxRate: String = "",
    var category: String = "",
    var image_link: String = ""
)
