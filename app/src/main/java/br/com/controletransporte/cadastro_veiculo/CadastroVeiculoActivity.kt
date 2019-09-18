package br.com.controletransporte.cadastro_veiculo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.view.View
import android.widget.TextView
import br.com.controletransporte.R
import br.com.controletransporte.util.MaskPlaca
import kotlinx.android.synthetic.main.activity_cadastro_veiculo.*

class CadastroVeiculoActivity : AppCompatActivity(), CadastroVeiculoView, View.OnClickListener {

    lateinit var presenterCadastroVeiculo: CadastroVeiculoPresenter
    lateinit var btn_cadastrar: TextView
    lateinit var btn_concluir: TextView

    lateinit var inputPlaca: TextInputLayout
    lateinit var inputEixos: TextInputLayout

    lateinit var idEmpresa: String

    override fun setPregressBar(isVisible: Boolean) {

        if(isVisible) {
            progressBar.visibility = View.VISIBLE
            return
        }

        progressBar.visibility = View.GONE
    }

    override fun clearInputs() {
        inputPlaca.editText?.setText("")
        inputEixos.editText?.setText("")
    }


    override fun errorPlaca(message: String) {
        inputPlaca.error = if(message.isEmpty()) null else message
    }

    override fun errorEixos(message: String) {
        inputEixos.error = if(message.isEmpty()) null else message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_veiculo)

        val intent: Intent = getIntent()
         idEmpresa = intent.getStringExtra("idEmpresa")

        val nome = intent.getStringExtra("nome")
        val segmento = intent.getStringExtra("segmento")
        val cep = intent.getStringExtra("cep")
        val estado = intent.getStringExtra("estado")
        val endereco = intent.getStringExtra("endereco")

        txt_nome.setText(nome)
        txt_segmento.setText(segmento)
        txt_cep.setText(cep)
        txt_estado.setText(estado)
        txt_endereco.setText(endereco)

        setView()

        presenterCadastroVeiculo = getPresenter()

        btn_cadastrar.setOnClickListener(this)
        btn_concluir.setOnClickListener(this)

    }

    fun setView() {
        btn_cadastrar = button_cadastrarVeiculo
        btn_concluir = button_concluir_cadastro

        inputPlaca = input_placa
        inputEixos = input_eixos

        inputPlaca.editText?.addTextChangedListener(MaskPlaca.insert(inputPlaca.editText!!))

    }

    fun getPresenter(): CadastroVeiculoPresenter {
        return CadastroVeiculoPresenterImpl(this,this)
    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.button_cadastrarVeiculo -> {
                presenterCadastroVeiculo.performCadastrar(
                    idEmpresa = idEmpresa,
                    placa = inputPlaca.editText?.text.toString(),
                    eixos = inputEixos.editText?.text.toString()
                )
            }

            R.id.button_concluir_cadastro -> {
                this.finish()
            }
        }
    }
}
