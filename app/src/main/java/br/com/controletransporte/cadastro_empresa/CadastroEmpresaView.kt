package br.com.controletransporte.cadastro_empresa

interface CadastroEmpresaView
{
    fun errorNome(message: String)
    fun errorCep(message: String)
    fun errorEndereco(message: String)

    fun clearInputs()
    fun setPregressBar(isVisible: Boolean)


}