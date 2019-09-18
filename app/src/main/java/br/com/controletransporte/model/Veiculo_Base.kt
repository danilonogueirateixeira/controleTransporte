package br.com.controletransporte.model

import com.google.gson.annotations.SerializedName

data class Veiculo_Base (

    @SerializedName("success") val success : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : List<Veiculo>
)