package br.com.caelum.twittelumappweb.api

import android.util.Log
import br.com.caelum.twittelumappweb.modelo.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

class TweetApi(retrofit: Retrofit) {
    private val service: TweetService by lazy { retrofit.create(TweetService::class.java) }

    fun cria(tweet: Tweet) {

        service.salva(tweet).enqueue(object : Callback<Tweet> {
            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                Log.i("salvando tweet", "${t.message}")
            }

            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                Log.i("salvando tweet - code", "${response.code()}")
                Log.i("salvando tweet - body", "${response.body()}")
                Log.i("salvando tweet - error", "${response.errorBody()}")
            }

        })
    }

    private interface TweetService {

        @POST("/tweet")
        fun salva(@Body tweet: Tweet): Call<Tweet>

    }
}
