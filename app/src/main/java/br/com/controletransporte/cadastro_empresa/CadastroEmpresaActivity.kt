package br.com.controletransporte.cadastro_empresa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.*
import br.com.controletransporte.R
import kotlinx.android.synthetic.main.activity_cadastro_empresa.*
import br.com.controletransporte.util.MaskCep

class CadastroEmpresaActivity : AppCompatActivity(), CadastroEmpresaView, View.OnClickListener {

    lateinit var presenterCadastroEmpresa: CadastroEmpresaPresenter

    lateinit var btn_cadastrar: TextView
    lateinit var btn_listar: TextView

    lateinit var inputNome: TextInputLayout
    lateinit var inputCep: TextInputLayout
    lateinit var inputEndereco: TextInputLayout

    var estado : String = "UF"
    val estados = arrayOf("UF", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO")

    override fun setPregressBar(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
            return
        }

        progressBar.visibility = View.GONE
    }

    override fun clearInputs() {
        inputNome.editText?.setText("")
        inputCep.editText?.setText("")
        inputEndereco.editText?.setText("")
    }


    override fun errorNome(message: String) {
        inputNome.error = if(message.isEmpty()) null else message
    }

    override fun errorCep(message: String) {
        inputCep.error = if(message.isEmpty()) null else message
    }

    override fun errorEndereco(message: String) {
        inputEndereco.error = if(message.isEmpty()) null else message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_empresa)

        setView()

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            })

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            estados)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                estado = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>){
            }
        }

        presenterCadastroEmpresa = getPresenter()

        btn_cadastrar.setOnClickListener(this)
        btn_listar.setOnClickListener(this)
    }

    fun setView() {

        btn_cadastrar = button_cadastrar
        btn_listar = button_listar

        inputNome = input_nome
        inputCep = input_cep
        inputEndereco = input_endereco

        inputCep.editText?.addTextChangedListener(MaskCep.insert(inputCep.editText!!))
    }

    fun getPresenter(): CadastroEmpresaPresenter {
        return CadastroEmpresaPresenterImpl(this,this)
    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.button_cadastrar -> {

                val id: Int = radio_group.checkedRadioButtonId
                if (id!=-1){

                    if  (estado != "UF"){
                        val radio:RadioButton = findViewById(id)

                        presenterCadastroEmpresa.performCadastrar(
                            nome = inputNome.editText?.text.toString(),
                            segmento = radio.text.toString(),
                            cep = inputCep.editText?.text.toString(),
                            estado = estado,
                            endereco = inputEndereco.editText?.text.toString())
                    }else{
                        Toast.makeText(applicationContext,this.getString(R.string.error_input_estado),
                            Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(applicationContext,this.getString(R.string.error_input_segmento),
                        Toast.LENGTH_LONG).show()
                }
            }

            R.id.button_listar -> {
                presenterCadastroEmpresa.performListar()
            }
        }
    }

    fun radio_button_click(view: View){
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
    }

}
