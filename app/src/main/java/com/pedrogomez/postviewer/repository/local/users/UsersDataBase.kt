package com.pedrogomez.postviewer.repository.local.users

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedrogomez.postviewer.models.db.UserTable

@Database(entities = [UserTable::class], version = 2)
abstract class UsersDataBase : RoomDatabase() {

        abstract fun hits(): UsersDao

}