package br.com.caelum.twittelumappweb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragments.BuscaTweetFragment
import br.com.caelum.twittelumappweb.fragments.ListaTweetFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener { itemSelecionado ->

            when (itemSelecionado.itemId) {
                R.id.menu_lista -> exibe(ListaTweetFragment())
                R.id.menu_busca -> exibe(BuscaTweetFragment())
                R.id.menu_mapa -> exibe(Fragment())
            }

            return@setOnNavigationItemSelectedListener true
        }
        bottomNavigation.selectedItemId = R.id.menu_lista
    }

    private fun exibe(fragment: Fragment) {
        val transacao = supportFragmentManager.beginTransaction()
        transacao.replace(R.id.frameDaTela, fragment)
        transacao.commit()

    }
}
