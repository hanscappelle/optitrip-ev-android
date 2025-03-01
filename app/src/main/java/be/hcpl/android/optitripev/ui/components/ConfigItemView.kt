package be.hcpl.android.optitripev.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.ui.model.Config
import be.hcpl.android.optitripev.ui.model.ConfigUnit
import be.hcpl.android.optitripev.ui.model.ConfigValue
import be.hcpl.android.optitripev.ui.theme.AppTheme
import be.hcpl.android.optitripev.util.Constants.Companion.KM_TO_MI
import be.hcpl.android.optitripev.util.Constants.Companion.WH_KM_TO_WH_MI
import kotlin.math.round

fun Int.formattedSpeedValue(u: ConfigUnit) = if (u == ConfigUnit.Imperial) round(this * KM_TO_MI).toInt() else this

fun Float.convertedValue(u: ConfigUnit) = if (u == ConfigUnit.Imperial) this * WH_KM_TO_WH_MI else this.toDouble()

fun Float.formattedValue(u: ConfigUnit) = round(convertedValue(u) * 1000) / 1000

fun Float.readableValue(u: ConfigUnit) = round(this.convertedValue(u) * 100_000) / 100

fun Float.commonValue(u: ConfigUnit) = round(this.convertedValue(u) * 10_000) / 100

@Composable
fun ConfigItemView(unit: ConfigUnit, config: ConfigValue, onValueChange: (String) -> Unit) {
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
            onValueChange = onValueChange,
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
            ConfigItemView(ConfigUnit.Imperial, ConfigValue(30, 0.027f), {})
        }
    }
}