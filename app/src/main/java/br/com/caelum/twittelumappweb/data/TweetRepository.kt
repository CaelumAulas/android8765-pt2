package br.com.caelum.twittelumappweb.data

import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetRepository {

    fun salva(tweet: Tweet) {}

    fun lista() = listOf<Tweet>(
            Tweet("Oi"),
            Tweet("tchau"),
            Tweet("catchau"),
            Tweet("café"),
            Tweet("banana"),
            Tweet("pizzaaaaa"),
            Tweet("frio")
    )

    fun filtra(newText: String): List<Tweet> {
        val tweets = lista()
        return tweets.filter { tweet -> tweet.mensagem.contains(newText, true) }
    }

}
