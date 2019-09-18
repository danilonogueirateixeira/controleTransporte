package br.com.controletransporte.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://prova.cnt.org.br/kh10/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    fun empresaService(): EmpresaService {
        return retrofit.create(EmpresaService::class.java)
    }

    fun veiculoService(): VeiculoService {
        return retrofit.create(VeiculoService::class.java)
    }
}