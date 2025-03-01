package be.hcpl.android.optitripev

import be.hcpl.android.optitripev.domain.LocalStorage
import be.hcpl.android.optitripev.ui.main.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainViewModel)

    factoryOf(::LocalStorage)

}