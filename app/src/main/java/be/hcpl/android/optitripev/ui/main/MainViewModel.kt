package be.hcpl.android.optitripev.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.ui.model.Config
import be.hcpl.android.optitripev.ui.model.ConfigUnit
import be.hcpl.android.optitripev.ui.model.ConfigValue
import be.hcpl.android.optitripev.ui.model.defaultConfigValues
import be.hcpl.android.optitripev.ui.navigation.NavigationItem
import be.hcpl.android.optitripev.ui.navigation.Screen

class MainViewModel(
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
        config.value = defaultConfig // TODO get from preferences instead
    }


}