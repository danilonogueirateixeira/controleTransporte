package br.com.controletransporte.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Empresa  (

    @SerializedName("idEmpresa") val idEmpresa: Int?,
    @SerializedName("nome") val nome: String,
    @SerializedName("segmento") val segmento: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("endereco") val endereco: String
) : Serializable