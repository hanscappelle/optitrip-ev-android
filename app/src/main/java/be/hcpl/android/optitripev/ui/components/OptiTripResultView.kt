package be.hcpl.android.optitripev.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.OptiTripResult
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun OptiTripResultView(unit: ConfigUnit, result: OptiTripResult) {
    Column(
        //verticalArrangement =
    ){
        Text(text = stringResource(R.string.result_best_speed, result.speed.toInt(), unit.speed))
        //Text(text = stringResource(R.string.result_final_speed_equiv, result.speedEquiv().toInt(), unit.speed))
        Text(text = stringResource(R.string.result_number_of_charges, result.numberOfCharges))
        Text(text = stringResource(R.string.result_total_trip_time, 100))
        Text(text = stringResource(R.string.result_charge_equiv_speed, 100, unit.speed))
        //Text(text = stringResource(R.string.result_total_charge_time, result.totalChargeTime))
        //Text(text = stringResource(R.string.result_total_driving_time, result.drivingTime))
        Text(text = stringResource(R.string.result_distance_charge_1, 100, unit.distance))
        Text(text = stringResource(R.string.result_distance_charge_2, 100, unit.distance))
        Text(text = stringResource(R.string.result_distance_charge_3, 100, unit.distance))
        //Text(text = stringResource(R.string.result_time_per_charge, result.timePerCharge))
        //Text(text = stringResource(R.string.result_total_time_per_charge, result.totalChargeTime))
        Text(text = stringResource(R.string.result_total_trip_energy, result.totalEnergy))
    }
}

@Preview(showBackground = true)
@Composable
fun OptiTripResultPreview() {
    AppTheme {
        OptiTripResultView(
            ConfigUnit.Metric,
            OptiTripResult(
                speed = 90.0,
                drivingTime = 10.0,
                totalEnergy = 100.0,
                extraEnergy = 20.0,
                totalChargeTime = 30.0,
                timePerCharge = 10.0,
                numberOfCharges = 3
            )
        )
    }
}