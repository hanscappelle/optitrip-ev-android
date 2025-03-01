package be.hcpl.android.optitripev

import be.hcpl.android.optitripev.domain.LocalStorage
import be.hcpl.android.optitripev.ui.home.HomeViewModel
import be.hcpl.android.optitripev.ui.main.MainViewModel
import be.hcpl.android.optitripev.ui.result.ResultViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ResultViewModel)

//    factoryOf(::Literals)
    factoryOf(::LocalStorage)

}