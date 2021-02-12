package myway.group.uzblogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import myway.group.uzblogs.adapter.PostAdapter
import myway.group.uzblogs.adapter.UserAdapter
import myway.group.uzblogs.model.PostModel
import myway.group.uzblogs.model.UserModel

class MainActivity : AppCompatActivity() {
    val userList = listOf(
        UserModel("",""),
        UserModel("",""),
        UserModel("",""),
        UserModel("",""),
        UserModel("",""),
        UserModel("",""),
        UserModel("",""),
        UserModel("",""),
        UserModel("",""),
        UserModel("","")
    )
    val postList = listOf(
        PostModel(""),
        PostModel(""),
        PostModel(""),
        PostModel(""),
        PostModel(""),
        PostModel(""),
        PostModel("")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerUsers.adapter = UserAdapter(userList)

        recyclerPosts.layoutManager = LinearLayoutManager(this)
        recyclerPosts.adapter = PostAdapter(postList)
    }
}