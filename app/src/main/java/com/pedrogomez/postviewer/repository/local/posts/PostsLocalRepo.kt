package com.pedrogomez.postviewer.repository.local.posts

import androidx.lifecycle.LiveData
import com.pedrogomez.postviewer.models.db.PostTable
import com.pedrogomez.postviewer.repository.PostsProvider
import com.pedrogomez.postviewer.repository.local.users.UsersDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PostsLocalRepo(
    private val postsDao: PostsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PostsProvider.LocalDataSource {

    override fun observeUsers(): LiveData<List<PostTable>> {

    }

    override fun getPostByUser(id: Int): LiveData<List<PostTable>> {

    }

    override suspend fun insert(userTable: PostTable) {

    }

    override suspend fun delete(userTable: PostTable) {

    }

    override suspend fun updateLocal(toInsert: List<PostTable>) {

    }
}