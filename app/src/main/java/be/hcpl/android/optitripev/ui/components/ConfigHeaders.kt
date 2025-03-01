package be.hcpl.android.optitripev.ui.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfigHeaders() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(4.dp),
    ) {
        Text(
            modifier = Modifier.weight(.25f),
            text = "Speed km/h"
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "Wh/km",
        )
        Text(
            modifier = Modifier.weight(.25f),
            text = "kWh/km"
        )
        Text(
            modifier = Modifier.weight(.25f),
            text = "kWh/100km"
        )
    }
}