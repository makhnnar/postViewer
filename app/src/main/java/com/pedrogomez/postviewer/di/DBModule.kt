package com.pedrogomez.postviewer.di

import androidx.room.Room
import com.pedrogomez.postviewer.repository.PostsProvider
import com.pedrogomez.postviewer.repository.UsersProvider
import com.pedrogomez.postviewer.repository.local.MyDataBase
import com.pedrogomez.postviewer.repository.local.posts.PostsLocalRepo
import com.pedrogomez.postviewer.repository.local.users.UsersLocalRepo
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
                androidApplication(),
                MyDataBase::class.java,
                "Hits.db"
        ).fallbackToDestructiveMigration()
        .build()
    }
}

val dbUsersProvider = module {
    single<UsersProvider.LocalDataSource>{
        UsersLocalRepo(
                get<MyDataBase>().users()
        )
    }
}

val dbPostsProvider = module{
    single<PostsProvider.LocalDataSource>{
        PostsLocalRepo(
                get<MyDataBase>().posts()
        )
    }
}