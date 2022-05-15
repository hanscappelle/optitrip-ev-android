package be.hcpl.android.optitripev.util

class Constants {

    companion object {

        const val STORED_CONSUMPTION_CONFIG = "userConsumptionConfig"
        const val RESULT_CALCULATED_EFFICIENCY = "resultCalculateEfficiency"
        const val MINUTES_TO_HOUR = 0.0166667
        const val RESULT_TOTAL_DRIVE_TIME = "resultTotalDriveTime"
        const val RESULT_TOTAL_CHARGE_TIME = "resultTotalChargeTime"
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

        val defaultSpeedByConsumption = mapOf(
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