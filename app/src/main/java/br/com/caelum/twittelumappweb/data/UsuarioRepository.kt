package br.com.caelum.twittelumappweb.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.api.UsuarioApi
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioRepository(private val api: UsuarioApi) {

    private val usuarioLiveData: MutableLiveData<Usuario> = MutableLiveData()

    fun getUsuarioLiveData(): LiveData<Usuario> = usuarioLiveData

    private val erroLiveData: MutableLiveData<Throwable> = MutableLiveData()

    fun getErroLiveData(): LiveData<Throwable> = erroLiveData

    fun criar(usuario: Usuario) {
        api.cria(usuario, funcaoSucesso = funcaoSucesso(), funcaoErro = funcaoErro())

    }

    private fun funcaoSucesso(): (Usuario) -> Unit {
        return { usuario ->
            usuarioLiveData.postValue(usuario)
        }
    }

    private fun funcaoErro(): (Throwable) -> Unit {
        return { throwable ->
            erroLiveData.postValue(throwable)
        }
    }

    fun logar(usuario: Usuario) {
        Log.i("logou", "$usuario")
    }

}
