package api

import android.os.Handler
import android.os.Looper
import android.util.Log
import api.dataClasses.OAuthToken
import api.dataClasses.ProductList
import di.DaggerAppComponent
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ApiRequest"

class ApiRequest {
    val ORGANIZATION_ID = 27
    var token = OAuthToken(null, null)

    val appComponent = DaggerAppComponent.builder().build()

    var fullCredentials = appComponent.loginData()
    val dataAccess = appComponent.dataAccess()
    val retrofitInstance = appComponent.retrofitInstance()

    fun createApiRequest() {
        val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder().header("Authorization",
                "${token.tokenType}${token.accessToken}")
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }.build()

        val request = retrofitInstance.getRetrofitInstance(okHttpClient).create(DataAPI::class.java)

        credentialsRequest(request)
    }

    fun credentialsRequest(request: DataAPI) {

        var a = request.postCredentials(fullCredentials.PASSWORD,
            fullCredentials.CLIENT_SECRET,
            fullCredentials.CLIENT_ID,
            fullCredentials.LOGIN)

        token = a.execute().body()!!
        dataRequest(request)
    }

    fun dataRequest(request: DataAPI) {
        val b = request.getItem(ORGANIZATION_ID)
        val responseBody = b.execute().body()!!
        Log.i(TAG,responseBody.toString())

        dataAccess.insertData(responseBody.productArray)
    }
}