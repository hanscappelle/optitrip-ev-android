package be.hcpl.android.optitripev.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.model.Config
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.OptiTripInput
import be.hcpl.android.optitripev.model.OptiTripResult
import be.hcpl.android.optitripev.ui.navigation.BottomNavigationBar
import be.hcpl.android.optitripev.ui.navigation.NavigationItem
import be.hcpl.android.optitripev.ui.navigation.Screen
import be.hcpl.android.optitripev.ui.screen.AboutScreen
import be.hcpl.android.optitripev.ui.screen.ConfigScreen
import be.hcpl.android.optitripev.ui.screen.HomeScreen
import be.hcpl.android.optitripev.ui.screen.ResultScreen
import be.hcpl.android.optitripev.ui.theme.AppTheme
import be.hcpl.android.optitripev.ui.theme.customColor2
import be.hcpl.android.optitripev.ui.theme.onPrimaryDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigationItems: List<NavigationItem>,
    onUrlSelected: (String) -> Unit,
    config: Config?,
    onUnitChanged: (Boolean) -> Unit,
    onValueChanged: (String, String) -> Unit,
    resetValues: () -> Unit,
    optimalResult: OptiTripResult?,
    input: OptiTripInput?,
    updateInput: (OptiTripInput) -> Unit,
) {
    val navController = rememberNavController()
    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) },
                    colors = topAppBarColors(
                        containerColor = customColor2,
                        titleContentColor = onPrimaryDark,
                        navigationIconContentColor = onPrimaryDark,
                        actionIconContentColor = onPrimaryDark,
                    )
                )
            },
            bottomBar = { BottomNavigationBar(navController, navigationItems) }
        ) { innerPadding ->

            val graph = navController.createGraph(startDestination = Screen.Home.route) {
                composable(route = Screen.Home.route) {
                    HomeScreen(
                        unit = config?.unit ?: ConfigUnit.Metric,
                        input = input,
                        updateInput = updateInput,
                        result = optimalResult,
                    )
                }
                composable(route = Screen.Result.route) {
                    ResultScreen(config?.unit ?: ConfigUnit.Metric, input, optimalResult)
                }
                composable(route = Screen.Config.route) {
                    ConfigScreen(
                        config = config,
                        onUnitChanged = onUnitChanged,
                        onValueChanged = onValueChanged,
                        resetValues = resetValues,
                    )
                }
                composable(route = Screen.About.route) {
                    AboutScreen(onUrlSelected = onUrlSelected)
                }
            }
            NavHost(
                navController = navController,
                graph = graph,
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            navigationItems = listOf(), {},
            config = Config(values = emptyList()), {}, { key, value -> }, {},
            optimalResult = null,
            input = OptiTripInput(),
            updateInput = {},
        )
    }
}