package myway.group.uzblogs.model

data class PostModel(
        val id: String,
        val user_id: Int,
        val title: String,
        val created_at: String,
        val updated_at: String,
        val body: String
)