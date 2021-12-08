package com.pedrogomez.postviewer.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedrogomez.postviewer.models.db.PostTable
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.repository.local.posts.PostsDao
import com.pedrogomez.postviewer.repository.local.users.UsersDao

@Database(entities = [UserTable::class,PostTable::class], version = 2)
abstract class MyDataBase : RoomDatabase() {

        abstract fun users(): UsersDao
        abstract fun posts(): PostsDao

}