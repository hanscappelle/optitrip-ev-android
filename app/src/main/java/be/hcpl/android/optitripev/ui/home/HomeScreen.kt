package be.hcpl.android.optitripev.ui.home

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.ui.model.Config
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun HomeScreen(
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
            text = stringResource(R.string.header_input),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = stringResource(R.string.info_input),
            style = MaterialTheme.typography.bodyLarge,
        )

        // ... more input views, render configuration here

        // 3 options with optimal in the middle
        Text(text = stringResource(R.string.result_optimal_speed_alternative))
        Text(text = stringResource(R.string.result_optimal_speed))
        Text(text = stringResource(R.string.result_optimal_speed_alternative))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(Config(values = emptyList()))
    }
}