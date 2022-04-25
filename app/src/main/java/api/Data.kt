package api

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

class Data(@SerializedName("data") var product: JsonArray)