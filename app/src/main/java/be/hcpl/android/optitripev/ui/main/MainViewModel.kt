package be.hcpl.android.optitripev.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.domain.LocalStorage
import be.hcpl.android.optitripev.model.Config
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.ConfigValue
import be.hcpl.android.optitripev.model.defaultConfigValues
import be.hcpl.android.optitripev.ui.navigation.NavigationItem
import be.hcpl.android.optitripev.ui.navigation.Screen
import be.hcpl.android.optitripev.util.Constants

class MainViewModel(
    private val storage: LocalStorage,
) : ViewModel() {

    val config: MutableLiveData<Config> = MutableLiveData()

    // default app navigation configuration
    val navigationItems = listOf(
        NavigationItem(R.drawable.ic_input, R.string.title_home, Screen.Home),
        NavigationItem(R.drawable.ic_result, R.string.title_dashboard, Screen.Result),
        NavigationItem(R.drawable.ic_settings, R.string.title_notifications, Screen.Config),
        NavigationItem(R.drawable.ic_about, R.string.title_about, Screen.About),
    )

    // default values configuration
    val defaultConfig = Config(
        unit = ConfigUnit.Metric,
        values = defaultConfigValues.map { (key, value) -> ConfigValue(key, value.toFloat()) }
    )

    init {
        // get unit system from storage
        val unitFromStorage = if (storage.getBoolean(Constants.PREF_USE_METRIC)) ConfigUnit.Metric else ConfigUnit.Imperial
        // get current configuration values from storage
        val storedConfig = storage.getCurrentConfig()
        config.value = Config(
            unit = unitFromStorage,
            values = if ( storedConfig.isNotEmpty() ) storedConfig else defaultConfig.values,
        )
    }

    fun onUnitChanged(checked: Boolean) {
        config.value?.let {
            config.value = it.copy(unit = if (checked) ConfigUnit.Imperial else ConfigUnit.Metric)
        }
        storage.store(Constants.PREF_USE_METRIC, !checked)
    }

    fun onValueChanged(key: String, value: String) {
        config.value?.let {
            config.value = it.copy(
                values = it.values.map { if (it.atSpeed.toString() == key) it.copy(consumption = value.toFloat()) else it }
            )
        }
    }

    fun updateValuesInStorage() {
        config.value?.values?.let { storage.storeConfig(it) }
    }

    fun resetValues() {
        config.value = defaultConfig
    }


}