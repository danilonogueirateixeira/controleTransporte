package br.com.controletransporte.cadastro_empresa

interface CadastroEmpresaPresenter {

    fun performCadastrar(nome: String, segmento: String, cep: String, estado: String, endereco: String)

    fun performListar()

}