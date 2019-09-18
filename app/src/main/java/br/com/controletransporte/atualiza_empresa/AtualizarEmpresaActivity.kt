package br.com.controletransporte.atualiza_empresa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.*
import br.com.controletransporte.R
import br.com.controletransporte.lista_empresa.ListaEmpresaPresenterImpl
import br.com.controletransporte.model.Empresa
import br.com.controletransporte.util.MaskCep
import kotlinx.android.synthetic.main.activity_atualizar_empresa.button_cadastrar
import kotlinx.android.synthetic.main.activity_atualizar_empresa.input_cep
import kotlinx.android.synthetic.main.activity_atualizar_empresa.input_endereco
import kotlinx.android.synthetic.main.activity_atualizar_empresa.input_nome
import kotlinx.android.synthetic.main.activity_atualizar_empresa.radio_group
import kotlinx.android.synthetic.main.activity_atualizar_empresa.spinner
import java.io.Serializable

class AtualizarEmpresaActivity : AppCompatActivity() , AtualizarEmpresaView, View.OnClickListener {

    lateinit var btn_atualizar: TextView

    lateinit var inputNome: TextInputLayout
    lateinit var inputCep: TextInputLayout
    lateinit var inputEndereco: TextInputLayout

    lateinit var empresa : Empresa

    var estado : String = "UF"
    val estados = arrayOf("UF", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO")



    override fun clearInputs() {
        inputNome.editText?.setText("")
        inputCep.editText?.setText("")
        inputEndereco.editText?.setText("")

    }

    override fun errorNome(message: String) {
        inputNome.error = if (message.isEmpty()) null else message
    }

    override fun errorCep(message: String) {
        inputCep.error = if (message.isEmpty()) null else message
    }

    override fun errorEndereco(message: String) {
        inputEndereco.error = if (message.isEmpty()) null else message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atualizar_empresa)

        setView()

        radio_group.setOnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            estados
        )

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter;

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                estado = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>){
            }
        }

        val intent: Intent = getIntent()
        val dados : Serializable? = intent.getSerializableExtra("empresa")
        empresa = dados as Empresa

        inputNome.editText?.setText(empresa.nome)
        inputCep.editText?.setText(empresa.cep)
        inputEndereco.editText?.setText(empresa.endereco)

        btn_atualizar.setOnClickListener(this)
    }


    fun setView() {
        btn_atualizar = button_cadastrar

        inputNome = input_nome
        inputCep = input_cep
        inputEndereco = input_endereco

        inputCep.editText?.addTextChangedListener(MaskCep.insert(inputCep.editText!!))
    }

    fun radio_button_click(view: View){
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button_cadastrar -> {

                val listaEmpresaPresenterImpl = ListaEmpresaPresenterImpl()

                val id: Int = radio_group.checkedRadioButtonId
                if (id!=-1){

                    if  (estado != "UF"){
                        val radio:RadioButton = findViewById(id)

                        listaEmpresaPresenterImpl.editar(empresa = Empresa(
                            idEmpresa =  empresa.idEmpresa,
                            nome = inputNome.editText?.text.toString(),
                            segmento = radio.text.toString(),
                            cep = inputCep.editText?.text.toString(),
                            estado = estado,
                            endereco = inputEndereco.editText?.text.toString()))

                            this.finish()

                    }else{
                        Toast.makeText(applicationContext,"Selecione um Estado",
                            Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"Selecione um Segmento",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}