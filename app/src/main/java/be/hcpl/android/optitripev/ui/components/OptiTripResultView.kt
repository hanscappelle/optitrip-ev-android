package be.hcpl.android.optitripev.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.OptiTripInput
import be.hcpl.android.optitripev.model.OptiTripResult
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun OptiTripResultView(unit: ConfigUnit, input: OptiTripInput, result: OptiTripResult) {

    val distanceToChargers = result.distanceToChargers(input.initialSoc, input.usableEnergy, result.calculatedEfficiency)

    Column(
        verticalArrangement = spacedBy(8.dp)
    ) {
        Text(text = stringResource(R.string.result_best_speed, result.speed.toInt(), unit.speed))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_final_speed_equiv, result.speedEquiv(input.totalDistance, input.chargeDelay).toInt(), unit.speed))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_number_of_charges, result.numberOfCharges))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_total_trip_time, result.totalTime(input.chargeDelay).formatHours()))
        HorizontalDivider()
        Text(
            text = stringResource(
                R.string.result_charge_equiv_speed,
                result.chargeSpeedEquiv(input.usableEnergy, result.calculatedEfficiency).toInt(),
                unit.speed
            )
        )
        HorizontalDivider()
        Text(text = stringResource(R.string.result_total_charge_time, result.totalChargeTime.formatHours()))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_total_driving_time, result.drivingTime.formatHours()))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_distance_charge_1, distanceToChargers.first, unit.distance))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_distance_charge_2, distanceToChargers.second, unit.distance))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_distance_charge_3, distanceToChargers.third, unit.distance))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_time_per_charge, result.timePerCharge.formatHours()))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_total_time_per_charge, result.totalTimePerCharge(input.chargeDelay).formatHours()))
        HorizontalDivider()
        Text(text = stringResource(R.string.result_total_trip_energy, result.totalEnergy))
    }
}

@Preview(showBackground = true)
@Composable
fun OptiTripResultPreview() {
    AppTheme {
        OptiTripResultView(
            ConfigUnit.Metric,
            OptiTripInput(
            ),
            OptiTripResult(
                speed = 90.0,
                drivingTime = 10.0,
                totalEnergy = 100.0,
                extraEnergy = 20.0,
                totalChargeTime = 30.0,
                timePerCharge = 10.0,
                numberOfCharges = 3,
                calculatedEfficiency = 0.27f
            ),
        )
    }
}