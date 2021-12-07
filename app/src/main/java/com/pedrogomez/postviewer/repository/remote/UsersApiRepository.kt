package com.pedrogomez.postviewer.repository.remote


import com.pedrogomez.postviewer.models.api.post.PostResponse
import com.pedrogomez.postviewer.models.api.user.UserDataResponse
import com.pedrogomez.postviewer.repository.UsersProvider
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import com.pedrogomez.postviewer.utils.extensions.print

class UsersApiRepository(
    private val client : HttpClient,
    private val urlBase:String
) : UsersProvider.RemoteDataSource {

    override suspend fun getUsersRemoteData():List<UserDataResponse>?{
        return try{
            val requestUrl = "${urlBase}/users"
            "Ktor_request getHitsData: $requestUrl".print()
            val response = client.request<List<UserDataResponse>>(requestUrl) {
                method = HttpMethod.Get
            }
            "Ktor_request getHitsData: $response".print()
            response
        }catch (e : Exception){
            "Ktor_request Exception: ${e.message}".print()
            "Ktor_request Exception: ${e.printStackTrace()}".print()
            "Ktor_request Exception: ${e.cause}".print()
            null
        }
    }

}