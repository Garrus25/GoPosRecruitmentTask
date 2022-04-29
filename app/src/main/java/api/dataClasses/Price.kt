package api.dataClasses

import com.google.gson.annotations.SerializedName

data class Price(@SerializedName("amount") var amount: Int, @SerializedName("currency") val currency: String)
