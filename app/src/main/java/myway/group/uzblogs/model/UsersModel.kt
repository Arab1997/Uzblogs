package myway.group.uzblogs.model

import java.io.Serializable

data class UsersModel(
        val id: String,
        val email: String,
        val first_name: String,
        val last_name: String,
        val avatar: String
):Serializable