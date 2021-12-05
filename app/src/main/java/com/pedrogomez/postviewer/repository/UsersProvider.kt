package com.pedrogomez.postviewer.repository

import androidx.lifecycle.LiveData
import com.pedrogomez.postviewer.models.api.post.PostResponse
import com.pedrogomez.postviewer.models.api.user.UserDataResponse
import com.pedrogomez.postviewer.models.api.user.toPresentationModel
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.utils.extensions.print
import com.pedrogomez.postviewer.view.viewmodel.UsersViewModel

/**
 * this class is for get which repo is going to be consumed
 * */
class UsersProvider(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : UsersViewModel.Repository {

    override suspend fun loadUsers(page:Int) {
        val response = remoteDataSource.getUsersRemoteData()
        try{
            updateLocalWithRemote(
                response?.map(UserDataResponse::toPresentationModel) ?: ArrayList<UserTable>()
            )
        }catch (e:Exception){
            "Ktor_request loadHits: ${e.message}".print()
        }
    }

    override suspend fun filterUsersByName(name: String): LiveData<List<UserTable>> {
        return localDataSource.getUsersByName(name)
    }

    override suspend fun delete(userItem: UserTable) {
        localDataSource.delete(userItem)
    }

    override suspend fun getAllUsers() : List<UserTable> {
        return localDataSource.getAllUsers()
    }

    suspend fun updateLocalWithRemote(toInsert:List<UserTable>){
        localDataSource.updateLocal(toInsert)
    }

    override fun observeUsers(): LiveData<List<UserTable>> {
        return localDataSource.observeUsers()
    }

    interface LocalDataSource{
        suspend fun getAllUsers(): List<UserTable>
        suspend fun insert(userTable: UserTable)
        suspend fun delete(userTable: UserTable)
        fun observeUsers(): LiveData<List<UserTable>>
        fun getUsersByName(name:String): LiveData<List<UserTable>>
        suspend fun updateLocal(toInsert:List<UserTable>)
    }

    interface RemoteDataSource{
        suspend fun getUsersRemoteData(): List<UserDataResponse>?
        suspend fun getAllPost(): List<PostResponse>?
        suspend fun getPostByUser(id:Int): List<PostResponse>?
    }

}