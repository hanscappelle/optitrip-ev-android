package be.hcpl.android.optitripev.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.ui.navigation.NavigationItem
import be.hcpl.android.optitripev.ui.navigation.Screen

class MainViewModel(
) : ViewModel() {

    //val uiState: MutableLiveData<OverviewUiModel> = MutableLiveData<OverviewUiModel>()
    //val navigation: MutableLiveData<Navigation> = MutableLiveData()

    val navigationItems = listOf(
        NavigationItem(R.drawable.ic_input, R.string.title_home, Screen.Home),
        NavigationItem(R.drawable.ic_result, R.string.title_dashboard, Screen.Result),
        NavigationItem(R.drawable.ic_settings, R.string.title_notifications, Screen.Config),
        NavigationItem(R.drawable.ic_about, R.string.title_about, Screen.About),
    )

    init {

    }


}