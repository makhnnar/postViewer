package com.pedrogomez.postviewer.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.models.result.Result
import com.pedrogomez.postviewer.util.DataHelper
import com.pedrogomez.postviewer.util.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class UsersViewModelTest {

    private lateinit var SUT: UsersViewModel

    var reposotoryTD = RepositoryTD()

    val PAGE = 1

    @Before
    fun setUp() {
        SUT = UsersViewModel(
            reposotoryTD
        )
    }

    @Test
    fun setLoadingState_loaderDataUpdate() {
        runBlocking {
            SUT.setLoadingState()
            assertEquals(
                SUT.loaderData.getOrAwaitValue(),
                Result.Loading
            )
        }
    }

    @Test
    fun setSuccessState_loaderDataUpdate() {
        runBlocking {
            SUT.setSuccessState()
            assertEquals(
                SUT.loaderData.getOrAwaitValue(),
                Result.Success(true)
            )
        }
    }

    @Test
    fun saveSelected_selectedHitLiveDataUpdated() {
        runBlocking {
            SUT.saveSelected(DataHelper.HITTABLE)
            assertEquals(
                SUT.selectedHitLiveData.getOrAwaitValue(),
                DataHelper.HITTABLE
            )
        }
    }

    @Test
    fun delete_delete_successPassedParam() {
        runBlocking {
            SUT.delete(DataHelper.HITTABLE)
            assertEquals(
                reposotoryTD.toDelete,
                DataHelper.HITTABLE
            )
        }
    }

    @Test
    fun loadMore_loadHits_successPassedParam() {
        runBlocking {
            SUT.loadMore(PAGE)
            assertEquals(
                reposotoryTD.page,
                PAGE
            )
        }
    }

    class RepositoryTD : UsersViewModel.Repository{

        var toDelete : UserTable? = null

        var page = 0

        override suspend fun loadUsers(page: Int) {
            this.page = page
        }

        override suspend fun delete(userItem: UserTable) {
            toDelete = userItem
        }

        override suspend fun getAllUsers(): List<UserTable> {
            //never used
            return emptyList()
        }

        override fun observeUsers(): LiveData<List<UserTable>> {
            //used on viewmodel class creation. it's binding to DB
            return DataHelper.LIVEHISTDATA
        }

    }

}