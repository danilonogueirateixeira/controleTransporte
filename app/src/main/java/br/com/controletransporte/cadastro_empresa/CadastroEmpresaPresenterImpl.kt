package br.com.controletransporte.cadastro_empresa

import android.content.Context
import android.content.Intent
import android.widget.Toast
import br.com.controletransporte.lista_empresa.ListaEmpresaActivity
import br.com.controletransporte.R
import br.com.controletransporte.cadastro_veiculo.CadastroVeiculoActivity
import br.com.controletransporte.model.Empresa
import br.com.controletransporte.model.Empresa_Base
import br.com.controletransporte.retrofit.RetrofitInitializer
import br.com.dfsaude.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CadastroEmpresaPresenterImpl(val cadastroEmpresaView : CadastroEmpresaView, val context: Context) : CadastroEmpresaPresenter {

    override fun performCadastrar(nome: String, segmento: String, cep: String, estado: String, endereco: String) {

        if (!nome.isValidNome()) {
            cadastroEmpresaView.errorNome(context.getString(R.string.error_input_nome))
            return
        }
        cadastroEmpresaView.errorNome("")

        if (!cep.isValidCEP()) {
            cadastroEmpresaView.errorCep(context.getString(R.string.error_input_cep))
            return
        }
        cadastroEmpresaView.errorCep("")

        if (!endereco.isValidEndereco()) {
            cadastroEmpresaView.errorEndereco(context.getString(R.string.error_input_endereco))
            return
        }
        cadastroEmpresaView.errorEndereco("")

        adicionar(nome, segmento, cep, estado, endereco)
    }


    private fun adicionar(nome: String, segmento: String, cep: String, estado: String, endereco: String) {
        val call = RetrofitInitializer().empresaService().add(
            empresa = Empresa(
                idEmpresa = null,
                nome = nome,
                segmento = segmento,
                cep = cep,
                estado = estado,
                endereco = endereco)
        )

        cadastroEmpresaView.setPregressBar(true)

        call.enqueue(object : Callback<Empresa> {
            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                response.body()?.let {
                    Toast.makeText(context, "$nome cadastrada com sucesso!", Toast.LENGTH_LONG).show()
                    listaCadastro()
                }
            }
            override fun onFailure(call: Call<Empresa>, t: Throwable) {
                Toast.makeText(context, "Erro ao adicionar $nome", Toast.LENGTH_LONG).show()
                cadastroEmpresaView.setPregressBar(false)
            }
        })
    }

    private fun listaCadastro() {
        val call = RetrofitInitializer().empresaService().list()
        var empresa: Empresa?

        call.enqueue(object : Callback<Empresa_Base> {
            override fun onResponse(call: Call<Empresa_Base>, response: Response<Empresa_Base>) {
                response.body()?.let {
                    empresa = it.data.toList().last()
                    cadastroEmpresaView.setPregressBar(false)
                    cadastroEmpresaView.clearInputs()
                    val intent = Intent(context, CadastroVeiculoActivity::class.java)
                    intent.putExtra("idEmpresa", empresa?.idEmpresa.toString())
                    intent.putExtra("nome", empresa?.nome)
                    intent.putExtra("segmento", empresa?.segmento)
                    intent.putExtra("cep", empresa?.cep)
                    intent.putExtra("estado", empresa?.estado)
                    intent.putExtra("endereco", empresa?.endereco)

                    context.startActivity(intent)
                }

            }

            override fun onFailure(call: Call<Empresa_Base>, t: Throwable) {
                Toast.makeText(context, "Erro ao Prosseguir", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun performListar(){
                    cadastroEmpresaView.clearInputs()
                    val intent = Intent(context, ListaEmpresaActivity::class.java)
                    context.startActivity(intent)
    }
}