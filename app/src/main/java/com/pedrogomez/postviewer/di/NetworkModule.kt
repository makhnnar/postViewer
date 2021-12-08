package com.pedrogomez.postviewer.di

import com.pedrogomez.postviewer.R
import com.pedrogomez.postviewer.repository.PostsProvider
import com.pedrogomez.postviewer.repository.UsersProvider
import com.pedrogomez.postviewer.repository.remote.PostsApiRepository
import com.pedrogomez.postviewer.repository.remote.UsersApiRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val urlApi = module {
    single {
        androidApplication().getString(R.string.url_api)
    }
}

val networkModule = module {
    single { okHttpKtor }
    single<UsersProvider.RemoteDataSource> {
        UsersApiRepository(
            get(),
            get()
        )
    }
    single<PostsProvider.RemoteDataSource> {
        PostsApiRepository(
            get(),
            get()
        )
    }
}

private val okHttpKtor = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}