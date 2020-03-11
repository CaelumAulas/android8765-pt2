package br.com.caelum.twittelumappweb.data

import android.util.Log
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioRepository {
    fun criar(usuario: Usuario) {
        Log.i("criou", "$usuario")
    }

    fun logar(usuario: Usuario) {
        Log.i("logou", "$usuario")
    }

}
