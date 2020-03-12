package br.com.caelum.twittelumappweb.api

import br.com.caelum.twittelumappweb.modelo.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

class UsuarioApi(retrofit: Retrofit) {

    private val service: UsuarioService by lazy { retrofit.create(UsuarioService::class.java) }

    fun cria(
            usuario: Usuario,
            funcaoSucesso: (Usuario) -> Unit,
            funcaoErro: (Throwable) -> Unit
    ) {
        service.cria(usuario).enqueue(callback(funcaoErro, funcaoSucesso))
    }

    fun loga(
            usuario: Usuario,
            funcaoSucesso: (Usuario) -> Unit,
            funcaoErro: (Throwable) -> Unit
    ) {
        service.loga(usuario).enqueue(callback(funcaoErro, funcaoSucesso))
    }

    private fun callback(funcaoErro: (Throwable) -> Unit, funcaoSucesso: (Usuario) -> Unit): Callback<Usuario> {
        return object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, erro: Throwable) {
                funcaoErro(erro)
            }
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                if (response.isSuccessful) {
                    val usuario = response.body()
                    usuario?.let(funcaoSucesso)
                } else {
                    funcaoErro(Throwable(response.errorBody()?.string()))
                }
            }
        }
    }

    private interface UsuarioService {

        @POST("/usuario")
        fun cria(@Body usuario: Usuario): Call<Usuario>

        @POST("/usuario/login")
        fun loga(@Body usuario: Usuario): Call<Usuario>
    }
}
