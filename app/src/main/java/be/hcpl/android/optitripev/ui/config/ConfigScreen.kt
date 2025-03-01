package be.hcpl.android.optitripev.ui.config

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.ui.components.ConfigHeaders
import be.hcpl.android.optitripev.ui.components.ConfigItemView
import be.hcpl.android.optitripev.ui.model.Config
import be.hcpl.android.optitripev.ui.model.ConfigUnit

@Composable
fun ConfigScreen(
    config: Config,
    onUnitChanged: ((Boolean) -> Unit),
    resetValues: () -> Unit,
) {
    Column(
        verticalArrangement = spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        // info
        Text(
            text = stringResource(R.string.header_config),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = stringResource(R.string.info_config),
            style = MaterialTheme.typography.bodyLarge,
        )

        // allow unit selection here
        Text(text = stringResource(R.string.info_config_units))
        Row(
            horizontalArrangement = spacedBy(8.dp),
            verticalAlignment = CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.option_use_metric)
            )
            Switch(
                checked = config.unit == ConfigUnit.Imperial,
                onCheckedChange = onUnitChanged,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.option_use_imperial)
            )
        }

        ConfigHeaders(config)
        LazyColumn {
            config.values.forEach { v -> item { ConfigItemView(config.unit, v, {}) } }
            item {
                Row(verticalAlignment = CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.option_reset)
                    )
                    Button(
                        onClick = resetValues
                    ) {
                        Text(text = stringResource(R.string.label_reset))
                    }
                }
            }

        }

    }
}