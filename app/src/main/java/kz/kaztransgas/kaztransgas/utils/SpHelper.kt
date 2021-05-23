package kz.kaztransgas.kaztransgas.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SpHelper @SuppressLint("WrongConstant") protected constructor(context: Context) {
    private val spref: SharedPreferences
    protected fun setString(key: String?, value: String?) {
        if (value == null) {
            spref.edit().remove(key).apply()
        } else {
            spref.edit().putString(key, value).apply()
        }
    }

    protected fun getString(key: String?): String? {
        return spref.getString(key, "")
    }

    protected fun setInt(key: String?, value: Int) {
        spref.edit().putInt(key, value).apply()
    }

    protected fun getLong(key: String?): Long {
        return spref.getLong(key, 0)
    }

    protected fun getLong(key: String?, defaultValue: Long): Long {
        return spref.getLong(key, defaultValue)
    }

    protected fun setLong(key: String?, value: Long) {
        spref.edit().putLong(key, value).apply()
    }

    protected fun getInt(key: String?): Int {
        return spref.getInt(key, 0)
    }

    protected fun getInt(key: String?, defaultValue: Int): Int {
        return spref.getInt(key, defaultValue)
    }

    protected fun setBoolean(key: String?, value: Boolean) {
        spref.edit().putBoolean(key, value).apply()
    }

    protected fun getBoolean(key: String?): Boolean {
        return spref.getBoolean(key, false)
    }

    protected fun remove(key: String?) {
        spref.edit().remove(key).apply()
    }

    val isLoggedIn: Boolean
        get() = getString(api_token) != null && getString(api_token)?.isNotEmpty() ?: false

    fun setValue(key: String?, value: String?) {
        setString(key, value)
    }

    fun getValue(key: String?): String? {
        return getString(key)
    }

    var apiToken: String?
        get() = getString(api_token)
        set(session) {
            setString(api_token, session)
        }

    fun clearApiSession() {
        setString(api_token, "")
    }

    val isFirstRun: Boolean
        get() = !getBoolean(FIRST_RUN)

    fun setFirstRun() {
        setBoolean(FIRST_RUN, true)
    }


    var userName: String?
        get() = getString(USER_NAME)
        set(userName) {
            setString(USER_NAME, userName)
        }
    var userPhone: String?
        get() = getString(USER_PHONE)
        set(userPhone) {
            setString(USER_PHONE, userPhone)
        }
    var cityId: Int
        get() = getInt(CITY_ID)
        set(cityId) {
            setInt(CITY_ID, cityId)
        }
    var cityName: String?
        get() = getString(CITY_NAME)
        set(cityName) {
            setString(CITY_NAME, cityName)
        }
    var accountNumber:String?
    get() = getString(ACCOUNT_NUMBER)
    set(accountNumber){
        setString(ACCOUNT_NUMBER,accountNumber)
    }

    val isFirstTimeLogin: Boolean
        get() = !getBoolean(FIRST_TIME_LOGIN)

    fun setFirstTimeLogin() {
        setBoolean(FIRST_TIME_LOGIN, true)
    }


    var isDemo: Boolean
        get() = getBoolean(IS_DEMO)
        set(isDemo) {
            setBoolean(IS_DEMO, isDemo)
        }
    companion object {
        protected var spHelper: SpHelper? = null
        private const val CLEAR_LOCAL_STORAGE = "clearLocalStorage"
        private const val FIRST_RUN = "first_run"
        private const val USER_NAME = "userName"
        private const val USER_PHONE = "userPhone"
        private const val CITY_ID = "cityId"
        private const val CITY_NAME = "cityName"
        private const val FIRST_TIME_LOGIN = "first_time_login"
        private const val IS_DEMO = "isDemo"
        private const val  api_token = "apiToken"
        private const val ACCOUNT_NUMBER = "accountNumber"


        @JvmStatic
        fun getInstance(context: Context?): SpHelper {
            spHelper?.let {
                return it
            } ?: run {
                spHelper = SpHelper(context!!)
                return spHelper!!
            }
        }
    }

    init {
        spref = context.getSharedPreferences("my_pref", Context.MODE_APPEND)
    }
}
