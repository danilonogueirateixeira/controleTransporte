package br.com.controletransporte.lista_empresa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import br.com.controletransporte.R
import br.com.controletransporte.adapters.EmpresaAdapter
import br.com.controletransporte.cadastro_empresa.CadastroEmpresaView
import br.com.controletransporte.model.Empresa
import br.com.controletransporte.model.Empresa_Base
import br.com.controletransporte.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.empresa_lista.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaEmpresaActivity() : AppCompatActivity() , ListaEmpresaView {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onResume() {
        super.onResume()
        setView()
        listar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.empresa_lista)

        setView()
        listar()

    }

    override fun setPregressBar(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
            return
        }
        progressBar.visibility = View.GONE
    }

    fun setView() {
        recyclerView = empresa_list_recyclerview
        progressBar = progress_bar
        this.supportActionBar!!.title = "Empresas"
        setPregressBar(true)
    }

    fun configureList(empresas: MutableList<Empresa>) {
        val recyclerView = empresa_list_recyclerview
            recyclerView.adapter = EmpresaAdapter(empresas, this)
        val layoutManager = StaggeredGridLayoutManager(
            1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }


    fun listar(): List<Empresa>? {
        val call = RetrofitInitializer().empresaService().list()
        var empresas: List<Empresa>? = null

        call.enqueue(object : Callback<Empresa_Base> {
            override fun onResponse(call: Call<Empresa_Base>, response: Response<Empresa_Base>) {
                response.body()?.let {
                    empresas = it.data.toList()
                    if (it.data.size == 0){
                        Toast.makeText(this@ListaEmpresaActivity, "Nenhuma Empresa Cadastrada",
                            Toast.LENGTH_LONG).show()

                        this@ListaEmpresaActivity.finish()


                    }else {
                        configureList(it.data.toMutableList())
                        setPregressBar(false)
                    }
                }

            }
            override fun onFailure(call: Call<Empresa_Base>, t: Throwable) {
                Toast.makeText(this@ListaEmpresaActivity, "Erro ao Listar Empresas, tente novamente!", Toast.LENGTH_LONG).show()
            }
        })

        return empresas
    }


}
