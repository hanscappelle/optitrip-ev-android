package be.hcpl.android.optitripev.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.model.Config
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.ConfigValue
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun ConfigItemView(
    unit: ConfigUnit,
    config: ConfigValue,
    onValueChange: (String, String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(4.dp),
    ) {
        Text(
            modifier = Modifier.weight(.25f),
            text = "${config.atSpeed.formattedSpeedValue(unit)}"
        )
        TextField(
            modifier = Modifier.weight(1f),
            value = "${config.consumption.readableValue(unit)}",
            onValueChange = { value -> onValueChange(config.atSpeed.toString(), value.readableToStored(unit)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
        )
        Text(
            modifier = Modifier.weight(.25f),
            text = "${config.consumption.formattedValue(unit)}"
        )
        Text(
            modifier = Modifier.weight(.25f),
            text = "${config.consumption.commonValue(unit)}"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigItemViewPreview() {
    AppTheme {
        Column {
            ConfigHeaders(Config(values = emptyList()))
            ConfigItemView(ConfigUnit.Imperial, ConfigValue(30, 0.027f), { key, value -> {} })
        }
    }
}