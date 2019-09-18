package br.com.controletransporte.cadastro_veiculo


interface CadastroVeiculoPresenter {

    fun performCadastrar(idEmpresa: String, placa: String, eixos: String)
}