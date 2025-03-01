package be.hcpl.android.optitripev.domain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import be.hcpl.android.optitripev.model.ConfigValue
import be.hcpl.android.optitripev.util.Constants
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

interface Storage {

    fun get(key: String): String
    fun getString(key: String): String
    fun getBoolean(key: String): Boolean
    fun getInt(key: String): Int
    fun store(key: String, value: String)
    fun store(key: String, value: Boolean)
    fun store(key: String, value: Int)
    fun clearAll()
    fun getCurrentConfig(): List<ConfigValue>
    fun storeConfig(config: List<ConfigValue>)
}

class LocalStorage(
    context: Context,
) : Storage {

    private var prefs: SharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCES, MODE_PRIVATE)
    private val gson = GsonBuilder().setLenient().create()
    private val type = object : TypeToken<Map<Int, Double>>() {}.type


    override fun get(key: String) = getString(key)

    override fun getString(key: String) = prefs.getString(key, "").toString()

    override fun store(key: String, value: String) = prefs.edit().putString(key, value).apply()

    override fun getBoolean(key: String) = prefs.getBoolean(key, false)

    override fun store(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()

    override fun getInt(key: String) = prefs.getInt(key, 0)

    override fun store(key: String, value: Int) = prefs.edit().putInt(key, value).apply()

    override fun clearAll() = prefs.edit().clear().apply()

    override fun getCurrentConfig(): List<ConfigValue> {
        val currentConfigValues: Map<Int, Double> = gson.fromJson<Map<Int, Double>>(prefs.getString(Constants.STORED_CONSUMPTION_CONFIG, "[]"), type)
        return currentConfigValues.map { ConfigValue(atSpeed = it.key, consumption = it.value.toFloat()) }
    }

    override fun storeConfig(config: List<ConfigValue>){
        val newConfig = gson.toJson(config.associate { it.atSpeed to it.consumption.toDouble() }.toMap())
        prefs.edit().putString(Constants.STORED_CONSUMPTION_CONFIG, newConfig).apply()
    }

}