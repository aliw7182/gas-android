package kz.kaztransgas.kaztransgas.koin

import android.content.Context
import kz.kaztransgas.kaztransgas.http.RegistrationRepository
import kz.kaztransgas.kaztransgas.ui.LoginScreen.LoginScreen
import kz.kaztransgas.kaztransgas.ui.LoginScreen.LoginViewModel
import kz.kaztransgas.kaztransgas.ui.MainScreen.MainScreen
import kz.kaztransgas.kaztransgas.ui.MainScreen.ScanScreen
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrtionCodeScreen.RegistrationCodeScreen
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationInfoScreen.RegistrationInfoScreen
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationPhoneScreen.RegistrationPhoneScreen
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationScreen
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationInfoScreen.RegistrationInfoViewModel
import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrtionCodeScreen.RegistrationCodeViewModel
//import kz.kaztransgas.kaztransgas.ui.RegistrationScreen.RegistrationInfoScreen.RegistrationInfoScreen.RegistrationInfoViewModel
import kz.kaztransgas.kaztransgas.ui.welcomeScreen.WelcomeScreen
import kz.kaztransgas.kaztransgas.utils.*
import kz.kaztransgas.kaztransgas.utils.SpHelper.Companion.getInstance
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

class Modules(private val context: Context) {
    val appModule = module {
        fun context(): Context {
            return context
        }
        single { getInstance(get()) }

        fun fragmentMap(): FragmentMap {
            val fragmentMap = FragmentMap()
            fragmentMap[FragmentReference.WELCOME_SCREEN] = WelcomeScreen::class.java
            fragmentMap[FragmentReference.REGISTRATION_SCREEN] = RegistrationScreen::class.java
            fragmentMap[FragmentReference.REGISTRATION_INFO_SCREEN] =
                RegistrationInfoScreen::class.java
            fragmentMap[FragmentReference.REGISTRATION_PHONE_SCREEN] =
                RegistrationPhoneScreen::class.java
            fragmentMap[FragmentReference.REGISTRATION_CODE_SCREEN] =
                RegistrationCodeScreen::class.java
            fragmentMap[FragmentReference.LOGIN_SCREEN] = LoginScreen::class.java
            fragmentMap[FragmentReference.SCAN_SCREEN] = ScanScreen::class.java
            fragmentMap[FragmentReference.MAIN_SCREEN] = MainScreen::class.java
            return fragmentMap
        }
        single { fragmentMap() }

        fun activityMap(): ActivityMap {
            val activityMap = ActivityMap()
            return activityMap
        }

        single { activityMap() }



        fun retrofit(): Retrofit {
            val retrofitBuilder = RetrofitBuilder(getInstance(context))
            //retrofitBuilder.addLoggerInterceptor();
            return retrofitBuilder.build()
        }

        single {
            retrofit()
        }

        single {
            FragmentNavigator(get(), get())
        }
    }

    val registrationInfoModule = module {
        fun registrationRepository(retrofit: Retrofit): RegistrationRepository {
            return RegistrationRepository(retrofit)
        }
        factory {
            registrationRepository(retrofit = get())
        }

        viewModel {
            RegistrationInfoViewModel(registrationRepository = get())
        }

        viewModel {
            RegistrationCodeViewModel(get())
        }

        viewModel {
            LoginViewModel(get())
        }

    }
}