package br.com.controletransporte.retrofit

import br.com.controletransporte.model.Empresa
import br.com.controletransporte.model.Empresa_Base
import retrofit2.Call
import retrofit2.http.*


interface EmpresaService{

    @GET("empresa/listar/")
    fun list() : Call <Empresa_Base>

    @POST("empresa/cadastrar/")
    fun add(@Body empresa : Empresa): Call<Empresa>

    @POST("empresa/delete/")
    fun delete(@Body empresa: Empresa): Call<Empresa>

    @POST("empresa/editar/")
    fun edit(@Body empresa: Empresa): Call<Empresa>
}