package br.com.caelum.twittelumappweb.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : SupportMapFragment() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(activity!!, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        getMapAsync { mapa: GoogleMap ->
            viewModel.lista().observe(this, Observer { tweets ->

                tweets.map { it.toMarker() }.forEach { mapa.addMarker(it) }
            })
        }
    }
}

fun Tweet.toMarker(): MarkerOptions {
    val marcador = MarkerOptions()
    marcador.position(LatLng(this.latitude, this.longitude))
    marcador.title(this.dono.nome)
    marcador.snippet(this.mensagem)
    return marcador
}
