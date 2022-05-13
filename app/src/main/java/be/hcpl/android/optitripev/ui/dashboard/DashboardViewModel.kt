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
    val equivalentSpeed = MutableLiveData<Int>()
    val timePerCharge = MutableLiveData<Float>()
    val totalTimePerCharge = MutableLiveData<Float>()
    val distanceFirstCharger = MutableLiveData<Int>()
    val distanceSecondCharger = MutableLiveData<Int>()
    val distanceThirdCharger = MutableLiveData<Int>()

    override fun onResume(owner: LifecycleOwner) {
        // get stored calculation results here
        optimalSpeed.value = prefs.getInt(Constants.RESULT_OPTIMAL_SPEED, 0)
        totalTripEnergy.value = prefs.getFloat(Constants.RESULT_TOTAL_ENERGY, 0f)
        numberOfCharges.value = prefs.getInt(Constants.RESULT_NUMBER_OF_CHARGES, 0)
        val totalDriveTimeValue = prefs.getFloat(Constants.RESULT_TOTAL_DRIVE_TIME, 0f)
        val totalChargeTimeValue = prefs.getFloat(Constants.RESULT_TOTAL_CHARGE_TIME, 0f)
        val totalTripTimeValue = totalDriveTimeValue + totalChargeTimeValue
        totalChargeTime.value = totalChargeTimeValue
        totalDriveTime.value = totalDriveTimeValue
        totalTripTime.value = totalTripTimeValue
        val totalDistanceValue = prefs.getString(Constants.PREF_KEY_TOTAL_DISTANCE, "0")?.toInt() ?: 0 // from user input
        val equivalentSpeedValue = totalDistanceValue / totalTripTimeValue
        // FIXME charging equiv speed formula broken on mac
        equivalentSpeed.value = equivalentSpeedValue.toInt()
        // charge time
        val chargeTargetValue = prefs.getString(Constants.PREF_KEY_CHARGE_TARGET, "0")?.toInt() ?: 0
        val usableEnergyValue = prefs.getString(Constants.PREF_KEY_USABLE_ENERGY, "0.0")?.toDouble() ?: 0.0
        val chargePowerValue = prefs.getString(Constants.PREF_KEY_CHARGE_POWER, "0.0")?.toDouble() ?: 0.0
        val timePerChargeValue = (chargeTargetValue / 100)*(usableEnergyValue / chargePowerValue)
        timePerCharge.value = timePerChargeValue.toFloat()
        val overheadPerChargeValueInMin = prefs.getString(Constants.PREF_KEY_CHARGE_DELAY, "0.0")?.toDouble() ?: 0.0
        val overheadPerChargeValue = overheadPerChargeValueInMin * Constants.MINUTES_TO_HOUR
        val totalTimePerChargeValue = timePerChargeValue + overheadPerChargeValue
        totalTimePerCharge.value = totalTimePerChargeValue.toFloat()
        // distance to chargers
        val initialSocValue = prefs.getString(Constants.PREF_KEY_INITIAL_SOC, "0")?.toInt() ?: 0
        val calculatedEfficiencyValue = prefs.getFloat(Constants.RESULT_CALCULATED_EFFICIENCY, 0f)
        val distanceFirstChargerValue = (0.01 * initialSocValue * usableEnergyValue) / calculatedEfficiencyValue
        distanceFirstCharger.value = distanceFirstChargerValue.toInt()
        distanceSecondCharger.value = distanceFirstChargerValue.toInt() * 2
        distanceThirdCharger.value = distanceFirstChargerValue.toInt() * 3



    }

    override fun onPause(owner: LifecycleOwner) {
        // no actions needed so far
    }
}