package be.hcpl.android.optitripev.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.ui.model.Navigation

class MainViewModel(
) : ViewModel() {

    //val uiState: MutableLiveData<OverviewUiModel> = MutableLiveData<OverviewUiModel>()
    val navigation: MutableLiveData<Navigation> = MutableLiveData()

    init {
        getInitialData()
    }

    private fun getInitialData() {
        //uiState.postValue()
    }


}