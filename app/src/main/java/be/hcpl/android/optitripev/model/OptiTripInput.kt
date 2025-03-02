package be.hcpl.android.optitripev.model

data class OptiTripInput(
    val totalDistance: Float = 1000f, // unit km
    val chargePower: Float = 13f, // unit kW
    val chargeTarget: Float = 100f, // unit %
    val chargeDelay: Float = 0f, // unit minutes
    val usableEnergy: Float = 13f, // unit kWh
    val initialSoc: Float = 100f, // unit %
    val distFirstCharger: Float = 100f, // unit km
)