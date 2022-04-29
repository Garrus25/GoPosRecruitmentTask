package api.dataClasses

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("default_link") val default_link: String = "empty",
    @SerializedName("small") val small: String = "empty",
)
