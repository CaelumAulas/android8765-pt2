package br.com.caelum.twittelumappweb.servico

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MeuServico : Service() {

    override fun onBind(intent: Intent?): IBinder? = null



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "criei o serviço", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "matei o serviço", Toast.LENGTH_LONG).show()
    }
}
