package be.hcpl.android.optitripev.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import be.hcpl.android.optitripev.R
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.optitripev.ui.theme.AppTheme
import be.hcpl.android.optitripev.ui.theme.customColor2
import be.hcpl.android.optitripev.ui.theme.onPrimaryDark
import kotlin.let

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    title: String = stringResource(R.string.app_name),
    onBack: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = title) },
                    colors = topAppBarColors(
                        containerColor = customColor2,
                        titleContentColor = onPrimaryDark,
                        navigationIconContentColor = onPrimaryDark,
                        actionIconContentColor = onPrimaryDark,
                    ),
                    navigationIcon = {
                        onBack?.let {
                            IconButton(onClick = { onBack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.description_navigate_back)
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                NavigationView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(customColor2)
                        .padding(vertical = 4.dp)
                    ,
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                content()
            }

        }
    }
}

@Preview
@Composable
fun AppScaffoldPreview() {
    AppScaffold { }
}