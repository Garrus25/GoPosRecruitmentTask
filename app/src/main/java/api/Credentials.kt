package api

import com.example.goposrecruitmenttask.BuildConfig

data class Credentials(
    val CLIENT_ID: String = BuildConfig.client_id,
    val CLIENT_SECRET: String = BuildConfig.client_secret,
    val LOGIN: String = BuildConfig.login,
    val PASSWORD: String = BuildConfig.password
)
