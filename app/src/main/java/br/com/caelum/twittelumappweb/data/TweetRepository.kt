package br.com.caelum.twittelumappweb.data

import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.api.TweetApi
import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetRepository(private val api: TweetApi) {

    private val listaLiveData = MutableLiveData<List<Tweet>>()
    private val erroLiveData = MutableLiveData<Throwable>()

    fun salva(tweet: Tweet) {
        api.cria(tweet)
    }

    fun busca() = api.buscaLista(sucesso(), erro())

    fun sucesso() = { lista: List<Tweet> ->
        listaLiveData.postValue(lista)
    }

    fun erro() = { erro: Throwable ->
        erroLiveData.postValue(erro)
    }


    fun lista() = listaLiveData as LiveData<List<Tweet>>

    fun filtra(newText: String): List<Tweet> {
        listaLiveData.value?.let { tweets ->
            return tweets.filter { tweet -> tweet.mensagem.contains(newText, true) }
        }
        return emptyList()
    }

}
