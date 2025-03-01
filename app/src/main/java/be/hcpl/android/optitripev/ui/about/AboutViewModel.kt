package be.hcpl.android.optitripev.ui.about

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.ui.navigation.Navigation

class AboutViewModel(

) : ViewModel() {

    val navigation: MutableLiveData<Navigation> = MutableLiveData()

}