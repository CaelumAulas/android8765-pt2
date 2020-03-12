package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {


    fun salva(tweet: Tweet) = repository.salva(tweet)

    fun busca() = repository.busca()

    fun lista(): LiveData<List<Tweet>> = repository.lista()

    fun filtra(newText: String) = repository.filtra(newText)
}
