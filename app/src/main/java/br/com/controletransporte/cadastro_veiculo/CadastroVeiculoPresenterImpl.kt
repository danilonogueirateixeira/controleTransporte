package br.com.controletransporte.cadastro_veiculo

import android.content.Context
import android.util.Log
import android.widget.Toast
import br.com.controletransporte.R
import br.com.controletransporte.model.Veiculo
import br.com.controletransporte.retrofit.RetrofitInitializer
import br.com.dfsaude.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CadastroVeiculoPresenterImpl(val cadastroVeiculoView : CadastroVeiculoView, val context: Context) : CadastroVeiculoPresenter
{



    override fun performCadastrar(idEmpresa: String, placa: String, eixos: String)
    {

        if (!placa.isValidPlaca())
        {
            cadastroVeiculoView.errorPlaca(context.getString(R.string.error_input_placa))
            return
        }

        cadastroVeiculoView.errorPlaca("")

        if (!eixos.isValidEixos())
        {
            cadastroVeiculoView.errorEixos(context.getString(R.string.error_input_eixos))
            return
        }
        cadastroVeiculoView.errorEixos("")

        adicionar(idEmpresa, placa, eixos )
    }


    private fun adicionar(idEmpresa: String, placa: String, eixos: String) {
        val call = RetrofitInitializer().veiculoService().add(
            veiculo = Veiculo(
                idEmpresa = idEmpresa,
                idVeiculo = null,
                placa = placa,
                eixos = eixos,
                nome = null)
        )

        cadastroVeiculoView.setPregressBar(true)

        call.enqueue(object : Callback<Veiculo> {

            override fun onResponse(call: Call<Veiculo>, response: Response<Veiculo>) {
                response.body()?.let {
                    Toast.makeText(context, "Veículo adicionado com sucesso!", Toast.LENGTH_LONG).show()
                    cadastroVeiculoView.setPregressBar(false)
                    cadastroVeiculoView.clearInputs()
                }
            }

            override fun onFailure(call: Call<Veiculo>, t: Throwable) {
                Toast.makeText(context, "Erro ao adicionar veículo", Toast.LENGTH_LONG).show()
                cadastroVeiculoView.setPregressBar(false)
            }
        })
    }
}