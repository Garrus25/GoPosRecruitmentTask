package api

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Price,
    @SerializedName("name") val name: String,
    @SerializedName("tax_id") val tax_id: Int,
    @SerializedName("category_id") val category_id: String,
    @SerializedName("image_link") @NotNull val image: Image = Image("empty","empty"),
)