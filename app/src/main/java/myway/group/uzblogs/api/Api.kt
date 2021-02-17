package myway.group.uzblogs.api

import myway.group.uzblogs.model.PostModel
import myway.group.uzblogs.model.UserModel
import myway.group.uzblogs.model.UsersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Api {
   /* @Headers("api-id:602cb43775e0ac47cc8762a8")
    @GET("user")
    fun getUsers(): Call<BaseResponse<List<UserModel>>>*/

    @GET("users?page=2")
    fun getUsers(): Call<BaseResponse<List<UsersModel>>>

    @GET("users?delay=3")
    fun getPosts(): Call<BaseResponse<List<UsersModel>>>
}
