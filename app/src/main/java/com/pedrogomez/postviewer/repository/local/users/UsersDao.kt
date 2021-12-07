package com.pedrogomez.postviewer.repository.local.users

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pedrogomez.postviewer.models.db.UserTable

@Dao
interface UsersDao {

    @Query(value = "SELECT * from hit_table WHERE hit_table.isDeleted=:filter ORDER BY created_at_i DESC")
    fun getAllHits(filter:Boolean = false): List<UserTable>

    @Query(value = "SELECT * from hit_table WHERE hit_table.isDeleted=:filter ORDER BY created_at_i DESC")
    fun observeHits(filter:Boolean = false): LiveData<List<UserTable>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userTable: UserTable)

    //@Query(value = "UPDATE hit_table SET isDeleted='true' WHERE hit_table.objectID=:objectID")
    @Update
    fun delete(
            //objectID:String
        userTable: UserTable
    ):Int

}