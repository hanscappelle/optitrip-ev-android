package be.hcpl.android.optitripev.ui.model

sealed class Navigation {
    data object InputView : Navigation()
    data object ResultView : Navigation()
    data object SettingsView : Navigation()
    data object AboutApp : Navigation()
}