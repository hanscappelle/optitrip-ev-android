package be.hcpl.android.optitripev.ui.main

import be.hcpl.android.optitripev.R
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun MainScreen() {
    Column() {
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

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AppTheme {
        MainScreen()
    }
}