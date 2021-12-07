package com.pedrogomez.postviewer.models.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class PostTable(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    @NonNull
    @ColumnInfo(name = "body")
    val body: String,
    @NonNull
    @ColumnInfo(name = "title")
    val title: String,
    @NonNull
    @ColumnInfo(name = "userId")
    val userId: Int
)
