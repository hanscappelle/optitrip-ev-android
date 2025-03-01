package be.hcpl.android.optitripev.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import be.hcpl.android.optitripev.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import be.hcpl.android.optitripev.ui.theme.AppTheme

// TODO handle actions
// TODO mark selection

@Composable
fun NavigationView(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        NavItem(R.drawable.ic_input, R.string.title_home)
        NavItem(R.drawable.ic_result, R.string.title_dashboard)
        NavItem(R.drawable.ic_settings, R.string.title_notifications)
        NavItem(R.drawable.ic_about, R.string.title_about)
    }
}

@Composable
fun NavItem(
    @DrawableRes iconId: Int,
    @StringRes textId: Int,
) {
    val text = stringResource(textId)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = text,
        )
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationPreview() {
    AppTheme {
        NavigationView(modifier = Modifier.fillMaxWidth())
    }
}