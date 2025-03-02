package be.hcpl.android.optitripev.ui.screen

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.OptiTripInput
import be.hcpl.android.optitripev.model.OptiTripResult
import be.hcpl.android.optitripev.ui.components.OptiTripInputView
import be.hcpl.android.optitripev.ui.components.formatHours
import be.hcpl.android.optitripev.ui.components.toImperial
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun HomeScreen(
    unit: ConfigUnit,
    input: OptiTripInput?,
    updateInput: (OptiTripInput) -> Unit,
    result: OptiTripResult?,
) {
    Column(
        verticalArrangement = spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
        // info
        Text(
            text = stringResource(R.string.header_input),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = stringResource(R.string.info_input),
            style = MaterialTheme.typography.bodyLarge,
        )

        // input here
        input?.let {
            OptiTripInputView(
                unit = unit,
                input = input,
                updateInput = updateInput,
            )
        }
        // 3 options with optimal in the middle
        Text(
            text = stringResource(R.string.header_optimal_result),
            style = MaterialTheme.typography.titleLarge,
        )
        //Text(text = stringResource(R.string.result_optimal_speed_alternative))
        result?.let {
            Text(
                text = stringResource(
                    R.string.result_optimal_speed,
                    if (unit == ConfigUnit.Imperial) result.speed.toImperial().toInt() else result.speed.toInt(),
                    unit.speed,
                    result.totalTime(input?.chargeDelay ?: 0f).formatHours(),
                    result.numberOfCharges,
                )
            )
        }
        //Text(text = stringResource(R.string.result_optimal_speed_alternative))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(ConfigUnit.Metric, OptiTripInput(), {}, null)
    }
}