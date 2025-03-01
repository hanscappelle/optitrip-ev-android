package be.hcpl.android.optitripev.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import be.hcpl.android.optitripev.ui.model.Config
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity2 : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.config.observe(this, Observer<Config> { event -> onConfigChange(event) })
        setContent {
            MainScreen(
                navigationItems = viewModel.navigationItems,
                onUrlSelected = { url -> openUrl(url) },
                config = Config(values = emptyList()),
                onUnitChanged = {},
                resetValues = {},
            )
        }
    }

    private fun onConfigChange(config: Config) {
        setContent {
            MainScreen(
                navigationItems = viewModel.navigationItems,
                onUrlSelected = ::openUrl,
                config = config,
                onUnitChanged = ::onUnitChanged,
                resetValues = ::resetValues,
            )
        }
    }

    private fun onUnitChanged(checked: Boolean) {
        viewModel.onUnitChanged(checked)
    }

    private fun resetValues() {
        viewModel.resetValues()
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}