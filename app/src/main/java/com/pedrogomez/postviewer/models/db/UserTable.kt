package com.pedrogomez.postviewer.models.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hit_table")
data class UserTable(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int,
        @NonNull
        @ColumnInfo(name = "city")
        val city: String,
        @NonNull
        @ColumnInfo(name = "lat")
        val lat: String,
        @NonNull
        @ColumnInfo(name = "lng")
        val lng: String,
        @NonNull
        @ColumnInfo(name = "street")
        val street: String,
        @NonNull
        @ColumnInfo(name = "suite")
        val suite: String,
        @NonNull
        @ColumnInfo(name = "zipcode")
        val zipcode: String,
        @NonNull
        @ColumnInfo(name = "bs")
        val bs: String,
        @NonNull
        @ColumnInfo(name = "catchPhrase")
        val catchPhrase: String,
        @NonNull
        @ColumnInfo(name = "nameCompany")
        val nameCompany: String,
        @NonNull
        @ColumnInfo(name = "email")
        val email: String,
        @NonNull
        @ColumnInfo(name = "name")
        val name: String,
        @NonNull
        @ColumnInfo(name = "phone")
        val phone: String,
        @NonNull
        @ColumnInfo(name = "username")
        val username: String,
        @NonNull
        @ColumnInfo(name = "website")
        val website: String
)

/*fun HitTable.toPresentationModel() : Hit {
        return Hit(
                product_id,
                likes,
                like_user,
                address,
                price,
                currency,
                p_condition,
                category,
                title,
                description,
                attachment.url,
                attachment.thumbnail,
                created,
                owner
        )
}*/