package kz.kaztransgas.kaztransgas

import android.app.Application
import com.mikepenz.iconics.Iconics.init
import com.mikepenz.iconics.Iconics.registerFont
import kz.kaztransgas.kaztransgas.koin.Modules
import kz.kaztransgas.kaztransgas.utils.MaterialIcons
import kz.kaztransgas.kaztransgas.utils.SpHelper.Companion.getInstance
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init(applicationContext)
        registerFont(MaterialIcons())
        val modules = Modules(context = applicationContext)
        // start Koin context
        startKoin {
            androidContext(this@MainApplication)
            androidLogger(Level.NONE)
            modules(listOf(
                modules.appModule,
                modules.registrationInfoModule))
        }
        val spHelper = getInstance(applicationContext)
        spHelper.apiToken = null

    }
}