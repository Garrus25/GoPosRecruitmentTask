package api.dataClasses

import com.google.gson.annotations.SerializedName

data class Tax(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rate") val rate: String,
)
