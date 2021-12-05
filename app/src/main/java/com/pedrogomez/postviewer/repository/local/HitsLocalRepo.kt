package com.pedrogomez.postviewer.repository.local

import androidx.lifecycle.LiveData
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.repository.UsersProvider
import com.pedrogomez.postviewer.utils.extensions.print
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HitsLocalRepo(
    private val usersDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersProvider.LocalDataSource {

    override suspend fun getAllUsers() : List<UserTable> = withContext(ioDispatcher) {
        usersDao.getAllHits()
    }

    override suspend fun insert(userTable: UserTable) = withContext(ioDispatcher) {
        usersDao.insert(
                userTable
        )
    }

    override suspend fun delete(userTable: UserTable) = withContext(ioDispatcher) {
        userTable.isDeleted = true
        val result = usersDao.delete(
                userTable
        )
        "delete: ${userTable.objectID} => $result".print()
    }

    override fun observeUsers(): LiveData<List<UserTable>> {
        return usersDao.observeHits()
    }

    override suspend fun updateLocal(toInsert: List<UserTable>) {
        toInsert.forEach {
            insert(it)
        }
    }

}