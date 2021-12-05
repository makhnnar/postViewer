package com.pedrogomez.postviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.util.DataHelper
import com.pedrogomez.postviewer.util.getOrAwaitValue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

import org.junit.After
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersProviderTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var SUT: UsersProvider

    val PAGE = 1

    var remoteDataSourceTD = RemoteDataSourceTD()
    var localDataSourceTD =  LocalDataSourceTD()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        SUT = UsersProvider(
            remoteDataSourceTD,
            localDataSourceTD
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun onLoadHits_getHitsData_passedParamsSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.loadUsers(PAGE)
                remoteDataSourceTD.getUsersRemoteData(PAGE)
                assertEquals(remoteDataSourceTD.page,PAGE)
            }
        }
    }

    @Test
    fun onLoadHits_getHitsData_successResponse() {
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.loadUsers(PAGE)
                val response = remoteDataSourceTD.getUsersRemoteData(PAGE)
                assertEquals(response, DataHelper.HITSRESPONSE)
            }
        }
    }

    @Test
    fun onLoadHits_getHitsData_failedResponse() {
        failed()
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.loadUsers(PAGE)
                val response = remoteDataSourceTD.getUsersRemoteData(PAGE)
                assertNull(response)
            }
        }
    }

    @Test
    fun getAllHits_getAllHits_returnedSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                val list = SUT.getAllUsers()
                assertEquals(
                        list,
                        DataHelper.HITSLIST
                )
            }
        }
    }

    @Test
    fun delete_delete_passedParamsSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.delete(DataHelper.HITTABLE)
                assertEquals(
                        localDataSourceTD.userToDelete,
                        DataHelper.HITTABLE
                )
            }
        }
    }

    @Test
    fun observeHits_observeHits_returnedSuccess() {
        runBlocking {
            onDataChange()
            val list = SUT.observeUsers()
            assertEquals(
                list.getOrAwaitValue(),
                DataHelper.HITSLIST
            )
        }
    }

    @Test
    fun updateLocalWithRemote_insert_passedParamsSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.updateLocalWithRemote(
                        DataHelper.HITSRESPONSE.hits.map {
                             it.toPresentationModel()
                         }
                )
                assertEquals(
                        localDataSourceTD.listHits,
                        DataHelper.HITSRESPONSE.hits.map {
                            it.toPresentationModel()
                        }
                )
            }
        }
    }

    private fun failed() {
        remoteDataSourceTD.failed = true
    }

    private fun onDataChange() {
        localDataSourceTD.hitsMutLivDat.postValue(
                DataHelper.HITSLIST
        )
    }

    class RemoteDataSourceTD : UsersProvider.RemoteDataSource {

        var page = 0

        var failed = false

        override suspend fun getUsersRemoteData(page: Int): HitsListResponse? {
            this.page = page
            return if(failed) null else DataHelper.HITSRESPONSE
        }

    }

    class LocalDataSourceTD : UsersProvider.LocalDataSource{

        var userToDelete : UserTable? = null

        var hitsMutLivDat = MutableLiveData<List<UserTable>>()

        var inserted : UserTable? = null

        var listHits = ArrayList<UserTable>()

        override suspend fun getAllUsers(): List<UserTable> {
            return DataHelper.HITSLIST
        }

        override suspend fun insert(userTable: UserTable) {
            inserted = userTable
        }

        override suspend fun delete(userTable: UserTable) {
            userToDelete = userTable
        }

        override fun observeUsers(): LiveData<List<UserTable>> {
            return hitsMutLivDat
        }

        override suspend fun updateLocal(toInsert: List<UserTable>) {
            listHits.addAll(toInsert)
        }

    }

}