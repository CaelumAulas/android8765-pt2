package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.api.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.api.UsuarioApi
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import retrofit2.Retrofit
import java.lang.Exception

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val retrofit: Retrofit by lazy { InicializadorDoRetrofit.getRetrofit() }
    private val usuarioApi: UsuarioApi by lazy { UsuarioApi(retrofit) }
    private val usuarioRepository by lazy { UsuarioRepository(usuarioApi) }

    private val tweetRepository: TweetRepository by lazy { TweetRepository() }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        TweetViewModel::class.java -> TweetViewModel(tweetRepository) as T
        UsuarioViewModel::class.java -> UsuarioViewModel(usuarioRepository) as T
        else -> throw Exception()
    }

}
