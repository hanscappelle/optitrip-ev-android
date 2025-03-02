package be.hcpl.android.optitripev.ui.screen

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.OptiTripInput
import be.hcpl.android.optitripev.model.OptiTripResult
import be.hcpl.android.optitripev.ui.components.OptiTripResultView
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun ResultScreen(
    unit: ConfigUnit,
    input: OptiTripInput?,
    result: OptiTripResult?,
    ) {
    Column(
        verticalArrangement = spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
        // info
        Text(
            text = stringResource(R.string.header_info_output),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = stringResource(R.string.info_output),
            style = MaterialTheme.typography.bodyLarge,
        )
        // result
        result?.let { input?.let { OptiTripResultView(unit, input, result) } }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    AppTheme {
        ResultScreen(ConfigUnit.Metric, null, null)
    }
}