package be.hcpl.android.optitripev.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import be.hcpl.android.optitripev.ui.components.AppScaffold
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class AboutView : ComponentActivity() {

    private val viewModel: AboutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.navigation.observe(this, Observer<Navigation> { event -> onNavigation(event) })
        setContent {
            AppScaffold() {
                AboutScreen(/*onUrlClicked = { url -> openUrl(url) }*/)
            }
        }
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}