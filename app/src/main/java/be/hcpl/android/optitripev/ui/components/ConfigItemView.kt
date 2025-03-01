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
import be.hcpl.android.optitripev.ui.model.ConfigValue
import be.hcpl.android.optitripev.ui.theme.AppTheme
import kotlin.math.round

fun Float.formattedValue() = this
fun Float.readableValue() = round(this * 100_000)/100
fun Float.commonValue() = round(this * 10_000)/100

@Composable
fun ConfigItemView(config: ConfigValue) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(4.dp),
    ) {
        Text(
            modifier = Modifier.weight(.25f),
            text = "${config.atSpeed}"
        )
        TextField(
            modifier = Modifier.weight(1f),
            value = "${config.consumption.readableValue()}",
            onValueChange = { /* TODO */ },
        )
        Text(
            modifier = Modifier.weight(.25f),
            text = "${config.consumption.formattedValue()}"
        )
        Text(
            modifier = Modifier.weight(.25f),
            text = "${config.consumption.commonValue()}"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigItemViewPreview() {
    AppTheme {
        Column {
            ConfigHeaders()
            ConfigItemView(ConfigValue(30, 0.027f))
        }
    }
}