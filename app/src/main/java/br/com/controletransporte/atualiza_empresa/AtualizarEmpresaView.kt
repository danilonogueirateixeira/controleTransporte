package br.com.controletransporte.atualiza_empresa

interface AtualizarEmpresaView
{
    fun errorNome(message: String)
    fun errorCep(message: String)
    fun errorEndereco(message: String)

    fun clearInputs()
}