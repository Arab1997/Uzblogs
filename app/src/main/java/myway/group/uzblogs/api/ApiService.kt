package myway.group.uzblogs.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {


    //api ="602629e12d621a7ada18581e"
    var retrofit: Retrofit? = null  //boshlang'ch qiymat null ga teng

    fun apiClient():Api{
        if (retrofit == null){ //retrofit null ga teng bosa biron marta hali yaratilmagan bo'sa
            retrofit = Retrofit.Builder()
               // .baseUrl("https://dummyapi.io/data/api/")
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit!!.create(Api::class.java)
    }
}