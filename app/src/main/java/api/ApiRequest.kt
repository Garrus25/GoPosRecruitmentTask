package api

import android.util.Log
import com.google.gson.JsonObject
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "ApiRequest"

class ApiRequest {
    val ORGANIZATION_ID = 27
    var fullCredentials = Credentials()
    var basicCredentials =
        Credentials.basic(fullCredentials.CLIENT_ID, fullCredentials.CLIENT_SECRET)
    lateinit var parser: Parser

    var token = OAuthToken(null, null)

    fun createApiRequest() {
        val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder().header("Authorization",
                if (token != null) "${token.tokenType}${token.accessToken}" else basicCredentials)
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(DataAPI.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val request = retrofit.create(DataAPI::class.java)

        request.postCredentials(fullCredentials.PASSWORD,
            fullCredentials.CLIENT_SECRET,
            fullCredentials.CLIENT_ID,
            fullCredentials.LOGIN)?.enqueue(object : Callback<OAuthToken> {
            override fun onFailure(call: Call<OAuthToken>, t: Throwable) {
                Log.i(TAG, t.toString())
                Log.i(TAG, request.toString())
            }

            override fun onResponse(call: Call<OAuthToken>, response: Response<OAuthToken>) {
                Log.i(TAG, response.toString())
                if (response.isSuccessful) {
                    Log.i(TAG, response.body()?.accessToken.toString())
                    token = response.body()!!

                    request.getItem(ORGANIZATION_ID).enqueue(object : Callback<JsonObject> {
                        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                            Log.i(TAG, t.toString())
                        }

                        override fun onResponse(
                            call: Call<JsonObject>,
                            response: Response<JsonObject>,
                        ) {
                            Log.i(TAG, "json response " + response.body().toString())
                            parser = Parser(response.body()!!)
                            parser.parseData()
                        }
                    })
                }
            }
        })
    }
}