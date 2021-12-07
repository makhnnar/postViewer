package com.pedrogomez.postviewer.repository.remote

import com.pedrogomez.postviewer.models.api.post.PostResponse
import com.pedrogomez.postviewer.repository.PostsProvider
import com.pedrogomez.postviewer.utils.extensions.print
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostsApiRepository(
    private val client : HttpClient,
    private val urlBase:String
) : PostsProvider.RemoteDataSource {

    override suspend fun getAllPost(): List<PostResponse>? {
        return try{
            val requestUrl = "${urlBase}/posts"
            "Ktor_request getHitsData: $requestUrl".print()
            val response = client.request<List<PostResponse>>(requestUrl) {
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

    override suspend fun getPostByUser(id: Int): List<PostResponse>? {
        return try{
            val requestUrl = "${urlBase}/posts?userId=${id}"
            "Ktor_request getHitsData: $requestUrl".print()
            val response = client.request<List<PostResponse>>(requestUrl) {
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