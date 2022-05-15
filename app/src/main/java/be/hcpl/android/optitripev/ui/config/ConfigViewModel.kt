package be.hcpl.android.optitripev.ui.config

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.util.Constants

class ConfigViewModel(application: Application) : AndroidViewModel(application),
    DefaultLifecycleObserver {

    private val context by lazy { getApplication<Application>().applicationContext }
    private val prefs = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

    // TODO allow for endless entries? In case user wants to add different speeds?
    val speedValues = MutableLiveData<List<Int>>()
    val consumptionValues = MutableLiveData<List<Double>>()

    override fun onPause(lifecycleOwner: LifecycleOwner){

    }

    override fun onResume(lifecycleOwner: LifecycleOwner){
        // TODO move this table to a configurable view so users can have their own
        consumptionValues.value = Constants.defaultSpeedByConsumption.values.toList()
        speedValues.value = Constants.defaultSpeedByConsumption.keys.toList()

    }

    fun recoverDefaults() {
        // recover defaults from config
        consumptionValues.value = Constants.defaultSpeedByConsumption.values.toList()
        speedValues.value = Constants.defaultSpeedByConsumption.keys.toList()
    }


}