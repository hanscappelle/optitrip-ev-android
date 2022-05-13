package be.hcpl.android.optitripev.ui.dashboard

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.util.Constants

class DashboardViewModel(application: Application) : AndroidViewModel(application),
    DefaultLifecycleObserver {

    private val context by lazy { getApplication<Application>().applicationContext }
    private val prefs = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

    val optimalSpeed = MutableLiveData<Int>()
    val totalTripEnergy = MutableLiveData<Float>()
    val numberOfCharges = MutableLiveData<Int>()
    val totalTripTime = MutableLiveData<Float>()
    val totalDriveTime = MutableLiveData<Float>()
    val totalChargeTime = MutableLiveData<Float>()

    override fun onResume(owner: LifecycleOwner) {
        // TODO get stored calculation results here
        optimalSpeed.value = prefs.getInt(Constants.RESULT_OPTIMAL_SPEED, 0)
        totalTripEnergy.value = prefs.getFloat(Constants.RESULT_TOTAL_ENERGY, 0f)
        numberOfCharges.value = prefs.getInt(Constants.RESULT_NUMBER_OF_CHARGES, 0)
        val totalDriveTimeValue = prefs.getFloat(Constants.RESULT_TOTAL_DRIVE_TIME, 0f)
        val totalChargeTimeValue = prefs.getFloat(Constants.RESULT_TOTAL_CHARGE_TIME, 0f)
        totalChargeTime.value = totalChargeTimeValue
        totalDriveTime.value = totalDriveTimeValue
        totalTripTime.value = totalDriveTimeValue + totalChargeTimeValue
    }

    override fun onPause(owner: LifecycleOwner) {
        // no actions needed so far
    }
}