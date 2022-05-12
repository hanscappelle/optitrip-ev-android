package be.hcpl.android.optitripev.ui.home

import android.app.Application
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.R

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    val totalDistance = MutableLiveData<String>()
    val chargePower = MutableLiveData<String>()

    val result = MutableLiveData<String>()

    // TODO store values in preferences and recover on resume
    // TODO parse values from text, add errors where needed

    fun calculate() {
        // TODO perform calculation

        val optimalSpeed = 95
        result.value = String.format(context.getString(R.string.result_optimal_speed), optimalSpeed)
    }
}