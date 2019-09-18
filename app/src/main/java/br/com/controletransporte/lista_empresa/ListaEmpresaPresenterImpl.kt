package br.com.controletransporte.lista_empresa

import android.util.Log
import br.com.controletransporte.model.Empresa
import br.com.controletransporte.model.Empresa_Base
import br.com.controletransporte.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaEmpresaPresenterImpl : ListaEmpresaPresenter {



    override fun deletar(idEmpresa: Empresa) {

        val call = RetrofitInitializer().empresaService().delete(idEmpresa)

        /* REALIZANDO CHAMADA ASSINCRONA */
        call.enqueue(object : Callback<Empresa> {
            /* RESPOSTA DE SUCESSO DA CHAMADA */
            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                Log.e("DELETAR SUCESSO", response.message())
                response.body()?.let {
                    Log.e("DELETAR SUCESSO", it.toString())
                }
            }

            /* RESPOSTA DE ERRO DA CHAMADA */
            override fun onFailure(call: Call<Empresa>, t: Throwable) {
                Log.e("DELETAR ERRO", t.message)
            }
        })
    }

    override fun editar(empresa: Empresa) {

        val call = RetrofitInitializer().empresaService().edit(empresa)

        /* REALIZANDO CHAMADA ASSINCRONA */
        call.enqueue(object : Callback<Empresa> {
            /* RESPOSTA DE SUCESSO DA CHAMADA */
            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                Log.e("DELETAR SUCESSO", response.message())
                response.body()?.let {
                    Log.e("DELETAR SUCESSO", it.toString())
                }
            }

            /* RESPOSTA DE ERRO DA CHAMADA */
            override fun onFailure(call: Call<Empresa>, t: Throwable) {
                Log.e("DELETAR ERRO", t.message)
            }
        })
    }
}