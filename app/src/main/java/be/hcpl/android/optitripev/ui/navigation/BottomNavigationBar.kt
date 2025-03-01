package be.hcpl.android.optitripev.ui.navigation

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import be.hcpl.android.optitripev.ui.theme.customColor1
import be.hcpl.android.optitripev.ui.theme.customColor2
import be.hcpl.android.optitripev.ui.theme.primaryLight

/**
 * Author: Santosh Yadav
 * Created on: 16-11-2024 09:32
 */

@Composable
fun BottomNavigationBar(
    navController: NavController,
    navigationItems: List<NavigationItem>,
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.title),
                        tint = Color.Black,
                    )
                },
                label = {
                    Text(
                        stringResource(item.title),
                        color = if (index == selectedNavigationIndex.intValue)
                            Color.Black
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
}

data class NavigationItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    val route: Screen,
)

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Result : Screen("result_screen")
    object Config : Screen("config_screen")
    object About : Screen("about_screen")
}