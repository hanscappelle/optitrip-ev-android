package be.hcpl.android.optitripev.ui.about

import androidx.compose.foundation.layout.Arrangement
import be.hcpl.android.optitripev.R
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.ui.components.TextWithLink
import be.hcpl.android.optitripev.ui.theme.AppTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onUrlClicked: ((String) -> Unit),
    ) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.info_about_app)
        )
        TextWithLink(
            text = stringResource(R.string.info_about_people),
            onUrlClicked = { url -> onUrlClicked(url)},
        )
        TextWithLink(
            text = stringResource(R.string.info_about_testing),
            onUrlClicked = onUrlClicked,
        )
        Text(
            text = stringResource(R.string.info_about_future)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AppTheme {
        AboutScreen(onUrlClicked = {})
    }
}