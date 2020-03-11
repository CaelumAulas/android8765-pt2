package br.com.caelum.twittelumappweb.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.lista_tweet_fragment.*

class BuscaTweetFragment : Fragment() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(activity!!, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
            : View? {

        val view = inflater.inflate(R.layout.lista_tweet_fragment, container, false)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_busca_fragment, menu)
        val itemDeBusca = menu.findItem(R.id.busca_menu)
        val search = itemDeBusca.actionView as SearchView

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotBlank()) {
                    val listaFiltrada = viewModel.filtra(newText)
                    lista?.adapter = TweetAdapter(listaFiltrada)
                }
                return false
            }
        })
    }
}
