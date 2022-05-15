package be.hcpl.android.optitripev.ui.config

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import be.hcpl.android.optitripev.util.Constants
import be.hcpl.android.optitripev.util.toMetric
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class ConfigViewModel(application: Application) : AndroidViewModel(application),
    DefaultLifecycleObserver {

    private val context by lazy { getApplication<Application>().applicationContext }
    private val prefs = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    private val gson = GsonBuilder().setLenient().create()
    private val type = object: TypeToken<Map<Int, Double>>(){}.type

    // TODO allow for endless entries? In case user wants to add different speeds?
    val speedValues = MutableLiveData<List<Int>>()
    val consumptionValues = MutableLiveData<List<Double>>()
    val updateEnabled = MutableLiveData(false)
    val useMetricSystem = MutableLiveData(true)

    private val currentValues = emptyMap<Int, Double>().toMutableMap()

    override fun onPause(lifecycleOwner: LifecycleOwner){
        // ignored for now, should we auto save?
    }

    override fun onResume(lifecycleOwner: LifecycleOwner){
        // try to get config
        // what measurement system does user prefer?
        useMetricSystem.value = prefs.getBoolean(Constants.PREF_USE_METRIC, true)
        showCurrentValues()
    }

    private fun showCurrentValues() {
        // get these from config (as json) instead to keep user prefs
        val storedConfig = gson.fromJson<Map<Int, Double>>(prefs.getString(Constants.STORED_CONSUMPTION_CONFIG, "[]"), type)
        if(storedConfig?.isNotEmpty() == true){
            consumptionValues.value = storedConfig.values.toList()
            speedValues.value = storedConfig.keys.toList()
            currentValues.clear()
            currentValues.putAll(storedConfig)
        } else {
            // use defaults as a backup
            recoverDefaults()
        }
    }

    fun recoverDefaults() {
        // recover defaults from config
        consumptionValues.value = Constants.defaultSpeedByConsumption.values.toList()
        speedValues.value = Constants.defaultSpeedByConsumption.keys.toList()
        currentValues.clear()
        currentValues.putAll(Constants.defaultSpeedByConsumption)
        updateEnabled.value = true
    }

    fun updateValue(speed: Int, metric: Boolean, value: Double) {
        currentValues[speed] = if( metric ) value else value.toMetric()
        updateEnabled.value = true
    }

    fun storeNewValues() {
        val newConfig = gson.toJson(currentValues)
        prefs.edit().putString(Constants.STORED_CONSUMPTION_CONFIG, newConfig).apply()
        updateEnabled.value = false
    }

    fun useMetricSystem(checked: Boolean){
        prefs.edit()
            .putBoolean(Constants.PREF_USE_METRIC, checked)
            .apply()
        useMetricSystem.value = checked
        showCurrentValues()
    }

}