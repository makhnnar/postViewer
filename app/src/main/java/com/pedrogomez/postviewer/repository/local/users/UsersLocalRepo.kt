package com.pedrogomez.postviewer.repository.local.users

import androidx.lifecycle.LiveData
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.repository.UsersProvider
import com.pedrogomez.postviewer.utils.extensions.print
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersLocalRepo(
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
        val result = usersDao.delete(
                userTable
        )
    }

    override fun observeUsers(): LiveData<List<UserTable>> {
        return usersDao.observeHits()
    }

    override fun getUsersByName(name: String): LiveData<List<UserTable>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocal(toInsert: List<UserTable>) {
        toInsert.forEach {
            insert(it)
        }
    }

}