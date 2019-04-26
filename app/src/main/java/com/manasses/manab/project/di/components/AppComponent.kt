import android.app.Application
import com.manasses.manab.project.MyApp
import com.manasses.manab.project.di.modules.ActivityModule
import com.manasses.manab.project.di.modules.AppModule
import com.manasses.manab.project.di.modules.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : AppComponent
    }

    fun inject(app: MyApp)
}