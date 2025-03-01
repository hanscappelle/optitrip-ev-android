package be.hcpl.android.optitripev.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import be.hcpl.android.optitripev.ui.about.AboutView
import be.hcpl.android.optitripev.ui.components.AppScaffold
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity2 : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.navigation.observe(this, Observer<Navigation> { event -> onNavigation(event) })
        setContent {
            //AppScaffold {
            MainScreen()
            //}
        }
    }

}