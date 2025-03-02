package be.hcpl.android.optitripev.model

data class OptiTripResult(
    // speed for this result
    val speed: Double,
    // driving time = total distance / speed
    val drivingTime: Double,
    // total energy = speedByConsumption * total trip distance
    val totalEnergy: Double,
    // required extra energy = abs ( total energy - ( usable energy * initialSoc ) // also takes initial soc into account
    val extraEnergy: Double,
    // total charge time = abs ( required extra energy / charge power )
    val totalChargeTime: Double,
    // time per charge = ( chargeTarget / 100 ) * ( usableEnergy / chargePower )
    val timePerCharge: Double,
    // # charges = ceil ( required extra energy / ( charge power * time per charge ) )
    val numberOfCharges: Int,
) {
    // formula:
    // driving time + total charge time + ( # charges * charge delay )
    fun totalTime(chargeDelay: Int) = drivingTime + totalChargeTime + (numberOfCharges * chargeDelay)

    // speed equivalent, that is the average speed with the charge time taken into account
    fun speedEquiv(totalDistance: Int, chargeDelay: Int) = totalDistance / totalTime(chargeDelay)
}