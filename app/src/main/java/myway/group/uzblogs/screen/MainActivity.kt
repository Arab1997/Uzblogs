package myway.group.uzblogs.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import myway.group.uzblogs.R
import myway.group.uzblogs.adapter.UserAdapter
import myway.group.uzblogs.adapter.UserAdapterListener
import myway.group.uzblogs.adapter.UsersAdapter
import myway.group.uzblogs.api.ApiService
import myway.group.uzblogs.api.BaseResponse
import myway.group.uzblogs.model.UsersModel
import myway.group.uzblogs.screen.detail.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
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
          //TEST
         /*  recyclerPosts.layoutManager = LinearLayoutManager(this)
             recyclerPosts.adapter = PostAdapter(postList)*/

        swipe.setOnRefreshListener(this)
        swipe.isRefreshing = true
        loadUsers()
        loadPosts()
    }

    fun loadUsers(){
        ApiService.apiClient().getUsers().enqueue(object : Callback<BaseResponse<List<UsersModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<UsersModel>>>,
                response: Response<BaseResponse<List<UsersModel>>>
            ) {
                swipe.isRefreshing = false
                recyclerUsers.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false) //agar null kepqosa empty list bervoradi
                recyclerUsers.adapter = UsersAdapter(response.body()?.data ?: emptyList(),  object :UserAdapterListener{
                    override fun onClick(item: UsersModel) {
                        val intent  = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("extra_data", item)
                        startActivity(intent)
                    }
                })
            }
            override fun onFailure(call: Call<BaseResponse<List<UsersModel>>>, t: Throwable) {
                swipe.isRefreshing = false
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
                swipe.isRefreshing = false
                recyclerPosts.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false) //agar null kepqosa empty list bervoradi
                recyclerPosts.adapter = UsersAdapter(response.body()?.data ?: emptyList(), object: UserAdapterListener{
                    override fun onClick(item: UsersModel) {
                        val intent  = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("extra_data", item)
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(call: Call<BaseResponse<List<UsersModel>>>, t: Throwable) {
                swipe.isRefreshing = false
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onRefresh() {
        loadUsers()
        loadPosts()
    }
}