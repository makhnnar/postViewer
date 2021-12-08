package com.pedrogomez.postviewer

import android.app.Application
import com.pedrogomez.postviewer.di.*
import com.pedrogomez.postviewer.view.di.productsRepository
import com.pedrogomez.postviewer.view.di.viewModelListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PostViewerAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(
                this@PostViewerAplication
            )
            androidLogger()
            modules(
                listOf(
                    urlApi,
                    networkModule,
                    productsRepository,
                    viewModelListModule,
                    dbModule,
                    dbUsersProvider,
                    dbPostsProvider
                )
            )
        }
    }

}