package br.com.controletransporte.lista_empresa

import br.com.controletransporte.model.Empresa


interface ListaEmpresaPresenter {



    fun deletar(idEmpresa : Empresa)

    fun editar(empresa: Empresa)




}