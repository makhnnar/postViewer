package com.pedrogomez.postviewer.view.viewmodel

import androidx.lifecycle.*
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.models.result.Result
import kotlinx.coroutines.*

class UsersViewModel(
    private val repository: Repository
) : ViewModel(){

    private val _hitsListLiveData : LiveData<List<UserTable>> = repository.observeUsers()

    val hitsListLiveData = _hitsListLiveData

    val selectedHitLiveData = MutableLiveData<UserTable>()

    val loaderData = MutableLiveData<Result<Boolean>>()

    fun reloadContent(){
        setLoadingState()
        viewModelScope.launch {
            repository.loadUsers(0)
            setSuccessState()
        }
    }

    fun setLoadingState(){
        loaderData.postValue(
            Result.Loading
        )
    }

    fun setSuccessState(){
        loaderData.postValue(
            Result.Success(true)
        )
    }

    fun loadFirstPage(){
        loadMore(0)
    }

    fun loadMore(page:Int){
        setLoadingState()
        viewModelScope.launch {
            repository.loadUsers(page)
            setSuccessState()
        }
    }

    fun saveSelected(productItem: UserTable){
        selectedHitLiveData.value = productItem
    }

    fun delete(userItem: UserTable) {
        viewModelScope.launch {
            repository.delete(userItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    interface Repository{

        suspend fun loadUsers(page:Int)
        suspend fun filterUsersByName(name:String): LiveData<List<UserTable>>
        suspend fun delete(userItem: UserTable)
        suspend fun getAllUsers(): List<UserTable>
        fun observeUsers(): LiveData<List<UserTable>>

    }

}