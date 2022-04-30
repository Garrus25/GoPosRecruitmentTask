package di

import api.RetrofitInstance
import api.dataClasses.LoginData
import dagger.Component
import database.DataAccess
import javax.inject.Singleton

@Singleton
@Component interface AppComponent {
    fun dataAccess(): DataAccess
    fun loginData(): LoginData
    fun retrofitInstance(): RetrofitInstance
}