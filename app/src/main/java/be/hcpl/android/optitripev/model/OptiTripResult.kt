package be.hcpl.android.optitripev.model

import be.hcpl.android.optitripev.domain.Constants

data class OptiTripResult(
    // speed for this result
    val speed: Double, // km/h
    // efficiency value this result is based upon
    val calculatedEfficiency: Float,
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
        chargeDelay: Float, // minutes
    ) = drivingTime + totalChargeTime + (numberOfCharges * chargeDelay * Constants.MINUTES_TO_HOUR)

    // speed equivalent, that is the average speed with the charge time taken into account
    fun speedEquiv(
        totalDistance: Float, // km
        chargeDelay: Float, // minutes
    ) = totalDistance / totalTime(chargeDelay)

    fun chargeSpeedEquiv(
        usableEnergy: Float, // kWh
        calculatedEfficiency: Float, // number from table
    ) = usableEnergy / calculatedEfficiency

    fun totalTimePerCharge(
        chargeDelay: Float, // minutes
    ) = timePerCharge + chargeDelay * Constants.MINUTES_TO_HOUR

    // distance to chargers
    fun distanceToChargers(
        initialSoc: Float, // in %
        usableEnergy: Float, // kWh
        calculatedEfficiency: Float, // min
    ): Triple<Int, Int, Int> {
        val distanceFirstCharger = (0.01 * initialSoc * usableEnergy) / calculatedEfficiency
        return Triple(
            distanceFirstCharger.toInt(),
            distanceFirstCharger.toInt() * 2,
            distanceFirstCharger.toInt() * 3,
        )
    }
}