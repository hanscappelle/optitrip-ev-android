package be.hcpl.android.optitripev.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import be.hcpl.android.optitripev.ui.model.Navigation
import be.hcpl.android.optitripev.ui.model.Navigation.InputView
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity2 : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigation.observe(this, Observer<Navigation> { event -> onNavigation(event) })

        // TODO provide scaffold with nav bottom
        //val navView: BottomNavigationView = binding.navView
        //val navController = findNavController(R.id.nav_host_fragment_activity_main)
        //val appBarConfiguration = AppBarConfiguration(
        //    setOf(
        //        R.id.navigation_home, R.id.navigation_output, R.id.navigation_config, R.id.navigation_about
    }

    private fun onNavigation(nav: Navigation) {
        when (nav) {
            InputView -> startActivity(Intent(this, MainActivity2::class.java))
            Navigation.AboutApp -> TODO()
            Navigation.ResultView -> TODO()
            Navigation.SettingsView -> TODO()
        }
    }
}