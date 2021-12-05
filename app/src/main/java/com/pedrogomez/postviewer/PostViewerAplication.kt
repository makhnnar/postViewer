package com.pedrogomez.postviewer

import android.app.Application
import com.pedrogomez.postviewer.di.dbModule
import com.pedrogomez.postviewer.di.dbProvider
import com.pedrogomez.postviewer.di.networkModule
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
                    networkModule,
                    productsRepository,
                    viewModelListModule,
                    dbModule,
                    dbProvider
                )
            )
        }
    }

}