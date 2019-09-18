package br.com.controletransporte.detalhe_empresa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import br.com.controletransporte.R
import br.com.controletransporte.adapters.EmpresaAdapter
import br.com.controletransporte.adapters.VeiculoAdapter
import br.com.controletransporte.model.Empresa
import br.com.controletransporte.model.Veiculo
import br.com.controletransporte.model.Veiculo_Base
import br.com.controletransporte.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_detalhe.*
import kotlinx.android.synthetic.main.empresa_lista.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class DetalheActivity : AppCompatActivity() {

    lateinit var txtNome: TextView
    lateinit var txtSegmento: TextView
    lateinit var txtCep: TextView
    lateinit var txtEstado: TextView
    lateinit var txtEndereco: TextView
    lateinit var btnListar : TextView
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        setView()

        val intent: Intent = getIntent()
        val dados : Serializable? = intent.getSerializableExtra("empresa")

        Log.e("empresa", dados.toString())

        val empresa : Empresa = dados as Empresa

        txtNome.setText(empresa.nome)
        txtSegmento.setText(empresa.segmento)
        txtCep.setText(empresa.cep)
        txtEstado.setText(empresa.estado)
        txtEndereco.setText(empresa.endereco)

        btnListar.setOnClickListener {
            listar(empresa.idEmpresa.toString())
        }
    }

    fun setView() {
        txtNome = txt_nome
        txtSegmento = txt_segmento
        txtCep = txt_cep
        txtEstado = txt_estado
        txtEndereco = txt_endereco
        btnListar = button_listar_veiculos
        recyclerView = veiculo_list_recyclerview

        this.supportActionBar!!.title = "Detalhes"

    }

    fun listar(id: String): Call<Veiculo_Base> {
        val call = RetrofitInitializer().veiculoService().list(id)

        call.enqueue(object : Callback<Veiculo_Base> {
            override fun onFailure(call: Call<Veiculo_Base>, t: Throwable) {
                Toast.makeText(this@DetalheActivity, "Erro ao Listar Veículos, tente novamente!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Veiculo_Base>, response: Response<Veiculo_Base>) {
                response.body()?.data?.let {

                    if (it.size == 0) {
                        Toast.makeText(this@DetalheActivity, "Nenhum Veículo Cadastrado", Toast.LENGTH_LONG).show()
                    }else {
                        configureList(it)
                    }
                }
            }

        })
        return call
    }

    fun configureList(veiculos: List<Veiculo>) {
        val recyclerView = veiculo_list_recyclerview
        recyclerView.adapter = VeiculoAdapter(veiculos, this)
        val layoutManager = StaggeredGridLayoutManager(
            1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}
