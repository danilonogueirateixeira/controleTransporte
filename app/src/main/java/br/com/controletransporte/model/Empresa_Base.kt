package br.com.controletransporte.model

import com.google.gson.annotations.SerializedName

data class Empresa_Base (

    @SerializedName("success") val success : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : List<Empresa>
)