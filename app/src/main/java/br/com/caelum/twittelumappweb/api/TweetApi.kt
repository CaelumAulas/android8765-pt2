package br.com.caelum.twittelumappweb.api

import android.util.Log
import br.com.caelum.twittelumappweb.modelo.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
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

    fun buscaLista(
            funcaoSucesso: (List<Tweet>) -> Unit,
            funcaoErro: (Throwable) -> Unit
    ) {

        service.busca().enqueue(object : Callback<List<Tweet>> {
            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                funcaoErro(t)
            }

            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    funcaoSucesso(response.body()!!)
                } else {
                    funcaoErro(Throwable(response.errorBody()?.string()))
                }
            }

        })

    }

    private interface TweetService {

        @POST("/tweet")
        fun salva(@Body tweet: Tweet): Call<Tweet>

        @GET("/tweet")
        fun busca(): Call<List<Tweet>>

    }
}
