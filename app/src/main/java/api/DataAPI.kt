package api

import api.dataClasses.OAuthToken
import api.dataClasses.Product
import api.dataClasses.ProductList
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface DataAPI {

    companion object {
        const val BASE_URL = "https://demo2.gopos.pl/"
    }
    @GET("api/v3/{organizationId}/items?size=10&status=&page=0&sort=status%2Cdesc&id_from=0&id_to=20&date_from=2021-07-05T10%3A35%3A17&date_to=2022-07-05T10%3A35%3A17&include=tax%2Ccategory")
    fun getItem(@Path("organizationId") organizationId: Int): Call<ProductList>

    @GET("oauth/token")
    fun postCredentials(
        @Query("password", encoded = true) password: String?,
        @Query("client_secret", encoded = true) client_secret: String?,
        @Query("client_id", encoded = true) client_id: String?,
        @Query("username", encoded = true) login: String?,
        @Query("grant_type") grant_type : String = "password"
    ): Call<OAuthToken>
}