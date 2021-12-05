package com.pedrogomez.postviewer.models.api.user

import com.pedrogomez.postviewer.models.db.UserTable
import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun UserDataResponse.toPresentationModel() : UserTable {
    return UserTable(
        id,
        address.city,
        address.geo.lat,
        address.geo.lng,
        address.street,
        address.suite,
        address.zipcode,
        company.bs,
        company.catchPhrase,
        company.name,
        email,
        name,
        phone,
        username,
        website
    )
}