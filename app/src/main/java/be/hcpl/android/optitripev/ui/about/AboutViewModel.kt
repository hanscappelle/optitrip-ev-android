package be.hcpl.android.optitripev.ui.about

import android.R.attr.versionName
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import be.hcpl.android.optitripev.BuildConfig
import be.hcpl.android.optitripev.R

class AboutViewModel(application: Application) : AndroidViewModel(application) {

    private val context by lazy { getApplication<Application>().applicationContext }

    val appVersion = MutableLiveData<String>()

    fun getAppVersion() {
        //val versionCode = BuildConfig.VERSION_CODE;
        //val versionName = BuildConfig.VERSION_NAME
        //appVersion.value = context.getString(R.string.info_about_app_version)
    }

}