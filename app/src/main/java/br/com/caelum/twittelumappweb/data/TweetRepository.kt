package br.com.caelum.twittelumappweb.data

import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetRepository {

    fun salva(tweet: Tweet) {}

    fun lista() = listOf<Tweet>(
            Tweet("Oi"),
            Tweet("Oi"),
            Tweet("Oi"),
            Tweet("Oi"),
            Tweet("Oi"),
            Tweet("Oi"),
            Tweet("Oi")
    )

}
