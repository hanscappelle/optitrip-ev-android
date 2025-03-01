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
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.R

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = stringResource(R.string.header_input),
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = stringResource(R.string.info_input),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}