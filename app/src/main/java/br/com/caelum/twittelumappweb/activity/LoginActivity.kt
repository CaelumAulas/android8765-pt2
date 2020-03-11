package br.com.caelum.twittelumappweb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel: UsuarioViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(UsuarioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_criar.setOnClickListener { viewModel.criar(pegaUsuarioDaTela()) }
        login_entrar.setOnClickListener { viewModel.logar(pegaUsuarioDaTela()) }
    }


    private fun pegaUsuarioDaTela(): Usuario {

        val nome = login_campoNome.text.toString()
        val senha = login_campoSenha.text.toString()
        val username = login_campoUsername.text.toString()

        return Usuario(username = username, nome = nome, senha = senha)
    }
}
