package br.com.controletransporte.cadastro_veiculo

interface CadastroVeiculoView
{
    fun errorPlaca(message: String)
    fun errorEixos(message: String)


    fun clearInputs()
    fun setPregressBar(isVisible: Boolean)


}