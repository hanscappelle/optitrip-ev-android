package be.hcpl.android.optitripev.ui.main

import be.hcpl.android.optitripev.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import be.hcpl.android.optitripev.ui.about.AboutScreen
import be.hcpl.android.optitripev.ui.home.HomeScreen
import be.hcpl.android.optitripev.ui.navigation.BottomNavigationBar
import be.hcpl.android.optitripev.ui.navigation.Screen
import be.hcpl.android.optitripev.ui.theme.AppTheme
import be.hcpl.android.optitripev.ui.theme.customColor2
import be.hcpl.android.optitripev.ui.theme.onPrimaryDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

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
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->

            val graph =
                navController.createGraph(startDestination = Screen.Home.route) {
                    composable(route = Screen.Home.route) {
                        HomeScreen()
                    }
                    composable(route = Screen.Result.route) {
                        //CartScreen()
                    }
                    composable(route = Screen.Config.route) {
                        //SettingScreen()
                    }
                    composable(route = Screen.About.route) {
                        AboutScreen()
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
        MainScreen()
    }
}