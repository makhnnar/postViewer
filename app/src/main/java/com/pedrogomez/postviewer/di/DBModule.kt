package com.pedrogomez.postviewer.di

import androidx.room.Room
import com.pedrogomez.postviewer.repository.UsersProvider
import com.pedrogomez.postviewer.repository.local.UsersDataBase
import com.pedrogomez.postviewer.repository.local.HitsLocalRepo
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
                androidApplication(),
                UsersDataBase::class.java,
                "Hits.db"
        ).fallbackToDestructiveMigration()
        .build()
    }
}

val dbProvider = module{
    single<UsersProvider.LocalDataSource>{
        HitsLocalRepo(
                get<UsersDataBase>().hits()
        )
    }
}