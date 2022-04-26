package api

import com.google.gson.annotations.SerializedName

data class ProductList (@SerializedName("data") val productArray: ArrayList<Product>){

}