package be.hcpl.android.optitripev.ui.config

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.ui.components.ConfigHeaders
import be.hcpl.android.optitripev.ui.components.ConfigItemView
import be.hcpl.android.optitripev.ui.model.Config

@Composable
fun ConfigScreen(
    config: Config,
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

        // TODO allow unit selection here

        // TODO reset option needed here

        ConfigHeaders()
        LazyColumn {
            config.values.forEach { v -> item { ConfigItemView(v) } }
        }

    }
}