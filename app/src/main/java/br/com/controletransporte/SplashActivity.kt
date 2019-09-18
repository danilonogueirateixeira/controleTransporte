package br.com.controletransporte

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.controletransporte.cadastro_empresa.CadastroEmpresaActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, CadastroEmpresaActivity::class.java)
            this.startActivity(intent)
            this.finish()
        }, 3000)

    }
}
