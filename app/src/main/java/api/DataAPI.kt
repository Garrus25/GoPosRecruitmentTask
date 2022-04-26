package api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface DataAPI {

    companion object {
        const val BASE_URL = "https://demo2.gopos.pl/"
    }

    @GET("api/v3/{organizationId}/items/?include=value1%2Cvalue2")
    fun getItem(@Path("organizationId") organizationId: Int): Call<ProductList>

    @GET("oauth/token")
    fun postCredentials(
        @Query("password", encoded = true) password: String?,
        @Query("client_secret", encoded = true) client_secret: String?,
        @Query("client_id", encoded = true) client_id: String?,
        @Query("username", encoded = true) login: String?,
        @Query("grant_type") grant_type : String = "password"
    ): Call<OAuthToken>?
}