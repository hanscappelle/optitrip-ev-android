package be.hcpl.android.optitripev.model

data class OptiTripInput(
    val totalDistance: Int = 1000,
    val chargePower: Int = 13,
    val chargeTarget: Int = 100,
    val chargeDelay: Int = 0,
    val usableEnergy: Int = 13,
    val initialSoc: Int = 100,
    val distFirstCharger: Int = 100,
)