package be.hcpl.android.optitripev.domain

class Constants {

    companion object {
        const val UNIT_KM = "km"
        const val UNIT_MI = "mi"
        const val UNIT_MPH = "mph"
        const val UNIT_KPH = "km/h"
        const val MI_TO_KM = 1.60934f
        const val WH_KM_TO_WH_MI = 1.60934f
        const val KM_TO_MI = 0.621371f
        const val PREF_USE_METRIC = "useMetric"
        const val STORED_CONSUMPTION_CONFIG = "userConsumptionConfig"
        const val RESULT_CALCULATED_EFFICIENCY = "resultCalculateEfficiency"
        const val MINUTES_TO_HOUR = 0.0166667f
        const val RESULT_TOTAL_DRIVE_TIME = "resultTotalDriveTime"
        const val RESULT_TOTAL_CHARGE_TIME = "resultTotalChargeTime"
        const val RESULT_TOTAL_TRIP_TIME = "resultTotalTripTime"
        const val RESULT_NUMBER_OF_CHARGES = "resultNumberOfCharges"
        const val RESULT_TOTAL_ENERGY = "resultTotalEnergy"
        const val RESULT_OPTIMAL_SPEED = "resultOptimalSpeed"
        const val PREF_KEY_CHARGE_DELAY = "chargeDelay"
        const val PREF_KEY_USABLE_ENERGY = "usableEnergy"
        const val PREF_KEY_DISTANCE_FIRST_CHARGER = "distanceFirstCharger"
        const val PREF_KEY_INITIAL_SOC = "initialSoc"
        const val PREF_KEY_TOTAL_DISTANCE = "totalDistance"
        const val PREF_KEY_CHARGE_POWER = "chargePower"
        const val APP_PREFERENCES = "optitripprefs"
        const val PREF_KEY_CHARGE_TARGET = "chargeTarget"
    }
}