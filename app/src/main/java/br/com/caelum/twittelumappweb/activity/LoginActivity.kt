package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.servico.MeuServico
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

        startService(Intent(this, MeuServico::class.java))

        viewModel.getUsuario().observe(this, Observer {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        viewModel.getErro().observe(this, Observer {
            Toast.makeText(this, "${it.message}", Toast.LENGTH_LONG).show()
        })

        login_criar.setOnClickListener { viewModel.criar(pegaUsuarioDaTela()) }
        login_entrar.setOnClickListener { viewModel.logar(pegaUsuarioDaTela()) }
    }


    private fun pegaUsuarioDaTela(): Usuario {

        val nome = login_campoNome.text.toString()
        val senha = login_campoSenha.text.toString()
        val username = login_campoUsername.text.toString()

        return Usuario(username = username, nome = nome, senha = senha)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, MeuServico::class.java))
    }
}
