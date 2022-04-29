package api.dataClasses

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Price,
    @SerializedName("name") val name: String,
    @SerializedName("image_link") @NotNull val image: Image = Image("empty","empty"),
    @SerializedName("category") val category: Category = Category(0,"empty"),
    @SerializedName("tax") val tax: Tax = Tax(0,"empty","empty"),
    )