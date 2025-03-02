package be.hcpl.android.optitripev.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.OptiTripInput
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun OptiTripInputView(
    unit: ConfigUnit,
    input: OptiTripInput,
    updateInput: (OptiTripInput) -> Unit,
) {
    Column(
        verticalArrangement = spacedBy(4.dp)
    ) {
        Input(
            value = "${input.totalDistance}",
            hint = stringResource(R.string.hint_total_distance, unit.distance),
            onValueChange = { value -> updateInput(input.copy(totalDistance = value.toIntOrNull() ?: 0)) },
        )
        Input(
            value = "${input.chargePower}",
            hint = stringResource(R.string.hint_charge_power),
            onValueChange = { value -> updateInput(input.copy(chargePower = value.toIntOrNull() ?: 0)) },
        )
        Input(
            value = "${input.chargeTarget}",
            hint = stringResource(R.string.hint_charge_target),
            onValueChange = { value -> updateInput(input.copy(chargeTarget = value.toIntOrNull() ?: 0)) },
        )
        Input(
            value = "${input.chargeDelay}",
            hint = stringResource(R.string.hint_charge_delay),
            onValueChange = { value -> updateInput(input.copy(chargeDelay = value.toIntOrNull() ?: 0)) },
        )
        Input(
            value = "${input.usableEnergy}",
            hint = stringResource(R.string.hint_usable_energy),
            onValueChange = { value -> updateInput(input.copy(usableEnergy = value.toIntOrNull() ?: 0)) },
        )
        Input(
            value = "${input.initialSoc}",
            hint = stringResource(R.string.hint_initial_soc),
            onValueChange = { value -> updateInput(input.copy(initialSoc = value.toIntOrNull() ?: 0)) },
        )
        Input(
            value = "${input.distFirstCharger}",
            hint = stringResource(R.string.hint_distance_first_charger, unit.distance),
            onValueChange = { value -> updateInput(input.copy(distFirstCharger = value.toIntOrNull() ?: 0)) },
        )
    }
}

@Composable
private fun Input(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = { Text(text = hint) },
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
    )
}

@Preview(showBackground = true)
@Composable
fun OptiTripInputViewPreview() {
    AppTheme {
        OptiTripInputView(ConfigUnit.Metric, OptiTripInput(), {})
    }
}