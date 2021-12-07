package com.pedrogomez.postviewer.repository

import androidx.lifecycle.LiveData
import com.pedrogomez.postviewer.models.api.post.PostResponse
import com.pedrogomez.postviewer.models.db.PostTable

class PostsProvider(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {


    interface LocalDataSource{
        fun observeUsers(): LiveData<List<PostTable>>
        fun getPostByUser(id:Int): LiveData<List<PostTable>>
        suspend fun insert(userTable: PostTable)
        suspend fun delete(userTable: PostTable)
        suspend fun updateLocal(toInsert:List<PostTable>)
    }

    interface RemoteDataSource{

        suspend fun getAllPost(): List<PostResponse>?
        suspend fun getPostByUser(id:Int): List<PostResponse>?

    }

}