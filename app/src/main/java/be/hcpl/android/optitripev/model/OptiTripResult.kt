package be.hcpl.android.optitripev.model

import be.hcpl.android.optitripev.domain.Constants

data class OptiTripResult(
    // speed for this result
    val speed: Double, // km/h
    // driving time = total distance / speed
    val drivingTime: Double, // hours
    // total energy = speedByConsumption * total trip distance
    val totalEnergy: Double, // kWh
    // required extra energy = abs ( total energy - ( usable energy * initialSoc ) // also takes initial soc into account
    val extraEnergy: Double, // kWh
    // total charge time = abs ( required extra energy / charge power )
    val totalChargeTime: Double, // hours
    // time per charge = ( chargeTarget / 100 ) * ( usableEnergy / chargePower )
    val timePerCharge: Double, // hours
    // # charges = ceil ( required extra energy / ( charge power * time per charge ) )
    val numberOfCharges: Int, // times
) {
    // formula:
    // driving time + total charge time + ( # charges * charge delay )
    fun totalTime(
        chargeDelay: Int, // minutes
    ) = drivingTime + totalChargeTime + (numberOfCharges * chargeDelay * Constants.MINUTES_TO_HOUR)

    // speed equivalent, that is the average speed with the charge time taken into account
    fun speedEquiv(
        totalDistance: Int, // km
        chargeDelay: Int, // minutes
    ) = totalDistance / totalTime(chargeDelay)
}