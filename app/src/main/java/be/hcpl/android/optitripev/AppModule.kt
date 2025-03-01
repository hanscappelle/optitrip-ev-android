package be.hcpl.android.optitripev

import be.hcpl.android.optitripev.ui.view.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainViewModel)

//    factoryOf(::Literals)
//    factoryOf(::LocalStorage)

}