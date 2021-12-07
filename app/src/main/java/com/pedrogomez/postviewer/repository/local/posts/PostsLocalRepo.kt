package com.pedrogomez.postviewer.repository.local.posts

import androidx.lifecycle.LiveData
import com.pedrogomez.postviewer.models.db.PostTable
import com.pedrogomez.postviewer.repository.PostsProvider

class PostsLocalRepo : PostsProvider.LocalDataSource {

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