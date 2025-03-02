package be.hcpl.android.optitripev.domain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import be.hcpl.android.optitripev.model.ConfigValue
import be.hcpl.android.optitripev.model.OptiTripInput
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
    fun lastInput(): OptiTripInput
    fun storeInput(input: OptiTripInput)
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

    override fun lastInput() = OptiTripInput(
        totalDistance = prefs.getFloat(Constants.PREF_KEY_TOTAL_DISTANCE, 1000f),
        chargePower = prefs.getFloat(Constants.PREF_KEY_CHARGE_POWER, 13f),
        chargeTarget = prefs.getFloat(Constants.PREF_KEY_CHARGE_TARGET, 100f),
        chargeDelay = prefs.getFloat(Constants.PREF_KEY_CHARGE_DELAY, 0f),
        usableEnergy = prefs.getFloat(Constants.PREF_KEY_USABLE_ENERGY, 13f),
        initialSoc = prefs.getFloat(Constants.PREF_KEY_INITIAL_SOC, 100f),
        distFirstCharger = prefs.getFloat(Constants.PREF_KEY_DISTANCE_FIRST_CHARGER, 100f),
    )

    override fun storeInput(input: OptiTripInput) = with(input) {
        prefs.edit()
            .putFloat(Constants.PREF_KEY_TOTAL_DISTANCE, totalDistance)
            .putFloat(Constants.PREF_KEY_CHARGE_POWER, chargePower)
            .putFloat(Constants.PREF_KEY_CHARGE_TARGET, chargeTarget)
            .putFloat(Constants.PREF_KEY_CHARGE_DELAY, chargeDelay)
            .putFloat(Constants.PREF_KEY_USABLE_ENERGY, usableEnergy)
            .putFloat(Constants.PREF_KEY_INITIAL_SOC, initialSoc)
            .putFloat(Constants.PREF_KEY_DISTANCE_FIRST_CHARGER, distFirstCharger).apply()
    }

    override fun getCurrentConfig(): List<ConfigValue> {
        val currentConfigValues: Map<Int, Double> = gson.fromJson<Map<Int, Double>>(prefs.getString(Constants.STORED_CONSUMPTION_CONFIG, "[]"), type)
        return currentConfigValues.map { ConfigValue(atSpeed = it.key, consumption = it.value.toFloat()) }
    }

    override fun storeConfig(config: List<ConfigValue>) {
        val newConfig = gson.toJson(config.associate { it.atSpeed to it.consumption.toDouble() }.toMap())
        prefs.edit().putString(Constants.STORED_CONSUMPTION_CONFIG, newConfig).apply()
    }

    companion object {
        val defaultConfigValues = mapOf(
            30 to 0.027,
            35 to 0.029,
            40 to 0.032,
            45 to 0.034,
            50 to 0.037,
            55 to 0.041,
            60 to 0.045,
            65 to 0.049,
            70 to 0.053,
            75 to 0.058,
            80 to 0.063,
            85 to 0.068,
            90 to 0.075,
            95 to 0.081,
            100 to 0.089,
            105 to 0.097,
            110 to 0.105,
            115 to 0.115,
            120 to 0.125,
            125 to 0.136,
            130 to 0.148,
            135 to 0.162,
            140 to 0.176,
        )
    }

}