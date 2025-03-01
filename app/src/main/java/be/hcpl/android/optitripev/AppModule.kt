package be.hcpl.android.optitripev

import be.hcpl.android.optitripev.ui.about.AboutViewModel
import be.hcpl.android.optitripev.ui.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainViewModel)
    viewModelOf(::AboutViewModel)

//    factoryOf(::Literals)
//    factoryOf(::LocalStorage)

}