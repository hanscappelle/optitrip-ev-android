package be.hcpl.android.optitripev.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.model.Config

@Composable
fun ConfigHeaders(config: Config) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(4.dp),
    ) {
        Text(
            modifier = Modifier.weight(.25f),
            text = "Speed ${config.unit.speed}"
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "Wh/${config.unit.distance}",
        )
        Text(
            modifier = Modifier.weight(.25f),
            text = "kWh/${config.unit.distance}"
        )
        Text(
            modifier = Modifier.weight(.25f),
            text = "kWh/100${config.unit.distance}"
        )
    }
}