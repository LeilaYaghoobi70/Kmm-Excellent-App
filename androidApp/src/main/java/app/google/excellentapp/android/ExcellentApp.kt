package app.google.excellentapp.android

import android.app.Application
import app.google.excellentapp.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class ExcellentApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            startKoin {
                androidContext(this@ExcellentApp)
                androidLogger()
                modules(NetworkModule().module)
            }
        }
    }
}
