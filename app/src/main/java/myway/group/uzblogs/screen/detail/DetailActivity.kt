package myway.group.uzblogs.screen.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.swipe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.posts_item_layout.*
import myway.group.uzblogs.R
import myway.group.uzblogs.adapter.UserAdapter
import myway.group.uzblogs.adapter.UserAdapterListener
import myway.group.uzblogs.adapter.UsersAdapter
import myway.group.uzblogs.api.ApiService
import myway.group.uzblogs.api.BaseResponse
import myway.group.uzblogs.model.UserModel
import myway.group.uzblogs.model.UsersModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var user: UsersModel //   ruhsat beradi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        user = intent.getSerializableExtra("extra_data") as UsersModel

        tvTitle.text = user.first_name + " " + user.last_name
        swipe.setOnRefreshListener(this)
        loadPost()
    }

    override fun onRefresh() {
        loadPost()
    }

    fun loadPost() {
        swipe.isRefreshing = true

        ApiService.apiClient().getPostByUser(user.id).enqueue(object: Callback<BaseResponse<List<UsersModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<UsersModel>>>,
                response: Response<BaseResponse<List<UsersModel>>>
            ) {
                swipe.isRefreshing = false
                recyclerPosts.layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.VERTICAL, false) //agar null kepqosa empty list bervoradi
                recyclerPosts.adapter = UsersAdapter(response.body()?.data ?: emptyList(), object:
                    UserAdapterListener {
                    override fun onClick(item: UsersModel) {
                        val intent  = Intent(this@DetailActivity, DetailActivity::class.java)
                        intent.putExtra("extra_data", item)
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(call: Call<BaseResponse<List<UsersModel>>>, t: Throwable) {
                swipe.isRefreshing = false
                Toast.makeText(this@DetailActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }
}