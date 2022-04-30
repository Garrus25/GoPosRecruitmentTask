package di

import android.content.Context
import com.example.goposrecruitmenttask.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainActivityModule(var context: Context) {

    @Provides
    fun context(): Context {
        return context;
    }
}

@Component(modules = [MainActivityModule::class])
@Singleton
interface MainActivityComponent {
    var context: Context
    fun inject(mainActivity: MainActivity);
}