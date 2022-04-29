package api.dataClasses

import com.example.goposrecruitmenttask.BuildConfig

data class LoginData(
    /**
    Data is accessed from data.properties file which is not listed on github
    Create data.properties file in main directory of this project and fill it with your data.
    Example content of data.properties file:
    LOGIN = "YOUR_LOGIN"
    PASSWORD = "YOUR_PASSWORD"
    CLIENT_ID = "YOUR_CLIENT_ID"
    CLIENT_SECRET = "YOUR_CLIENT_SECRET"
     */
    val CLIENT_ID: String = BuildConfig.client_id,
    val CLIENT_SECRET: String = BuildConfig.client_secret,
    val LOGIN: String = BuildConfig.login,
    val PASSWORD: String = BuildConfig.password,
)
