package be.hcpl.android.optitripev.ui.result

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.util.Constants

class ResultViewModel(application: Application) : AndroidViewModel(application),
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
    val equivalentChargeSpeed = MutableLiveData<Int>()
    val timePerCharge = MutableLiveData<Float>()
    val totalTimePerCharge = MutableLiveData<Float>()
    val distanceFirstCharger = MutableLiveData<Int>()
    val distanceSecondCharger = MutableLiveData<Int>()
    val distanceThirdCharger = MutableLiveData<Int>()

    var useMetric = prefs.getBoolean(Constants.PREF_USE_METRIC, true)
    val errorMessage = MutableLiveData<String>()

    override fun onResume(owner: LifecycleOwner) {
        try {
            // update metric preference
            useMetric = prefs.getBoolean(Constants.PREF_USE_METRIC, true)
            // get stored calculation results here
            optimalSpeed.value = prefs.getInt(Constants.RESULT_OPTIMAL_SPEED, 0)
            totalTripEnergy.value = prefs.getFloat(Constants.RESULT_TOTAL_ENERGY, 0f)
            numberOfCharges.value = prefs.getInt(Constants.RESULT_NUMBER_OF_CHARGES, 0)
            val totalDriveTimeValue = prefs.getFloat(Constants.RESULT_TOTAL_DRIVE_TIME, 0f)
            val totalChargeTimeValue = prefs.getFloat(Constants.RESULT_TOTAL_CHARGE_TIME, 0f)
            val totalTripTimeValue = prefs.getFloat(Constants.RESULT_TOTAL_TRIP_TIME, 0f)
            totalChargeTime.value = totalChargeTimeValue
            totalDriveTime.value = totalDriveTimeValue
            totalTripTime.value = totalTripTimeValue
            val totalDistanceValue =
                prefs.getString(Constants.PREF_KEY_TOTAL_DISTANCE, "0")?.toDouble()
                    ?: 0.0 // from user input
            // equivalent speeds
            val equivalentSpeedValue = totalDistanceValue / totalTripTimeValue
            equivalentSpeed.value = equivalentSpeedValue.toInt()
            // usableEnergy/calculated efficiency
            val usableEnergyValue =
                prefs.getString(Constants.PREF_KEY_USABLE_ENERGY, "0.0")?.toDouble() ?: 0.0
            val calculatedEfficiencyValue = prefs.getFloat(Constants.RESULT_CALCULATED_EFFICIENCY, 0f)
            val equivalentChargeSpeedValue = usableEnergyValue / calculatedEfficiencyValue
            equivalentChargeSpeed.value = equivalentChargeSpeedValue.toInt()
            // charge time
            val chargeTargetValue =
                prefs.getString(Constants.PREF_KEY_CHARGE_TARGET, "0")?.toInt() ?: 0
            val chargePowerValue =
                prefs.getString(Constants.PREF_KEY_CHARGE_POWER, "0.0")?.toDouble() ?: 0.0
            val timePerChargeValue =
                (chargeTargetValue.toDouble() / 100) * (usableEnergyValue / chargePowerValue)
            timePerCharge.value = timePerChargeValue.toFloat()
            val overheadPerChargeValueInMin =
                prefs.getString(Constants.PREF_KEY_CHARGE_DELAY, "0.0")?.toDouble() ?: 0.0
            val overheadPerChargeValue = overheadPerChargeValueInMin * Constants.MINUTES_TO_HOUR
            val totalTimePerChargeValue = timePerChargeValue + overheadPerChargeValue
            totalTimePerCharge.value = totalTimePerChargeValue.toFloat()
            // distance to chargers
            val initialSocValue = prefs.getString(Constants.PREF_KEY_INITIAL_SOC, "0")?.toInt() ?: 0
            val distanceFirstChargerValue =
                (0.01 * initialSocValue * usableEnergyValue) / calculatedEfficiencyValue
            distanceFirstCharger.value = distanceFirstChargerValue.toInt()
            distanceSecondCharger.value = distanceFirstChargerValue.toInt() * 2
            distanceThirdCharger.value = distanceFirstChargerValue.toInt() * 3

            errorMessage.value = "" // clear errors

        } catch (e: Exception) {
            errorMessage.value = context.getString(R.string.err_calculating_result)
        }
    }

}