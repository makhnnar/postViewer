package com.pedrogomez.postviewer.view.di

import com.pedrogomez.postviewer.repository.UsersProvider
import com.pedrogomez.postviewer.view.viewmodel.UsersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsRepository = module {
    single<UsersViewModel.Repository> {
        UsersProvider(
                get(),
                get()
        )
    }
}

val viewModelListModule = module {
    viewModel {
        UsersViewModel(
            get()
        )
    }
}