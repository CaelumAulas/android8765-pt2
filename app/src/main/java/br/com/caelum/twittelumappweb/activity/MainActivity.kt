package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragments.BuscaTweetFragment
import br.com.caelum.twittelumappweb.fragments.ListaTweetFragment
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.busca()

        bottomNavigation.setOnNavigationItemSelectedListener { itemSelecionado ->

            when (itemSelecionado.itemId) {
                R.id.menu_lista -> exibe(ListaTweetFragment())
                R.id.menu_busca -> exibe(BuscaTweetFragment())
                R.id.menu_mapa -> exibe(Fragment())
            }

            return@setOnNavigationItemSelectedListener true
        }
        bottomNavigation.selectedItemId = R.id.menu_lista

        fabNovo.setOnClickListener {
            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)
        }
    }

    private fun exibe(fragment: Fragment) {
        val transacao = supportFragmentManager.beginTransaction()
        transacao.replace(R.id.frameDaTela, fragment)
        transacao.commit()

    }
}
