package be.hcpl.android.optitripev.model

data class OptiTripInput(
    val totalDistance: Int = 1000, // unit km
    val chargePower: Int = 13, // unit kW
    val chargeTarget: Int = 100, // unit %
    val chargeDelay: Int = 0, // unit minutes
    val usableEnergy: Int = 13, // unit kWh
    val initialSoc: Int = 100, // unit %
    val distFirstCharger: Int = 100, // unit km
)