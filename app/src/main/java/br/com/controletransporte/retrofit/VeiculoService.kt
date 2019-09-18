package br.com.controletransporte.retrofit

import br.com.controletransporte.model.Empresa
import br.com.controletransporte.model.Empresa_Base
import br.com.controletransporte.model.Veiculo
import br.com.controletransporte.model.Veiculo_Base
import retrofit2.Call
import retrofit2.http.*


interface VeiculoService{

    @GET("empresa/{idEmpresa}/veiculo/listar")
    fun list(@Path("idEmpresa") id : String) : Call <Veiculo_Base>

    @POST("veiculo/cadastrar/")
    fun add(@Body veiculo: Veiculo): Call<Veiculo>


}