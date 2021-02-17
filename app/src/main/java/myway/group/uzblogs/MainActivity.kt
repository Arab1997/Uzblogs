package myway.group.uzblogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import myway.group.uzblogs.adapter.PostAdapter
import myway.group.uzblogs.adapter.UserAdapter
import myway.group.uzblogs.adapter.UsersAdapter
import myway.group.uzblogs.api.ApiService
import myway.group.uzblogs.api.BaseResponse
import myway.group.uzblogs.model.PostModel
import myway.group.uzblogs.model.UsersModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    //TEST
   /* val userList = listOf(
        UsersModel("","","","",""),
        UsersModel("","","","",""),
        UsersModel("","","","",""),
        UsersModel("","","","",""),
        UsersModel("","","","",""),
        UsersModel("","","","",""),
        UsersModel("","","","",""),
        UsersModel("","","","","")
    )
    val postList = listOf(
        PostModel("", 1,"","","",""),
        PostModel("", 1,"","","",""),
        PostModel("", 1,"","","",""),
        PostModel("", 1,"","","",""),
        PostModel("", 1,"","","",""),
        PostModel("", 1,"","","",""),
        PostModel("", 1,"","","","")

    )*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      /*  recyclerPosts.layoutManager = LinearLayoutManager(this)
        recyclerPosts.adapter = PostAdapter(postList)*/

        loadUsers()
        loadPosts()
    }

    fun loadUsers(){
        ApiService.apiClient().getUsers().enqueue(object : Callback<BaseResponse<List<UsersModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<UsersModel>>>,
                response: Response<BaseResponse<List<UsersModel>>>
            ) {
                recyclerUsers.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                recyclerUsers.adapter = UserAdapter(response.body()?.data ?: emptyList())   //agar null kepqosa empty list bervoradi
            }

            override fun onFailure(call: Call<BaseResponse<List<UsersModel>>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun loadPosts(){
        ApiService.apiClient().getPosts().enqueue(object : Callback<BaseResponse<List<UsersModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<UsersModel>>>,
                response: Response<BaseResponse<List<UsersModel>>>
            ) {
                recyclerPosts.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                recyclerPosts.adapter = UsersAdapter(response.body()?.data ?: emptyList())   //agar null kepqosa empty list bervoradi
            }

            override fun onFailure(call: Call<BaseResponse<List<UsersModel>>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }
}