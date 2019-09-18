package br.com.controletransporte.model

import com.google.gson.annotations.SerializedName

data class Veiculo(

    @SerializedName("idVeiculo") val idVeiculo: Int?,
    @SerializedName("idEmpresa") val idEmpresa: String,
    @SerializedName("placa") val placa: String,
    @SerializedName("numeroEixos") val eixos: String,
    @SerializedName("nomeDaEmpresa") val nome: String?
)