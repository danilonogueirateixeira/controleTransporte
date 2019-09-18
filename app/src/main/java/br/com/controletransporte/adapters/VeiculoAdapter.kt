package br.com.controletransporte.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.controletransporte.R
import br.com.controletransporte.model.Veiculo
import kotlinx.android.synthetic.main.veiculo_item.view.*

class VeiculoAdapter(private val veiculos: List<Veiculo>,
                     private val context: Context
) : RecyclerView.Adapter<VeiculoAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val veiculo = veiculos[p1]
        p0?.let {
            it.bindView(veiculo)

//            it.itemView.setOnClickListener {
//                Log.e("TESTE CLIQUE CARRO", veiculos[p1].toString())
//            }

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.veiculo_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return veiculos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(veiculo: Veiculo) {
            val placa = itemView.txt_placa
            val eixos = itemView.txt_eixos
            val carga = itemView.txt_carga

            placa.text = veiculo.placa
            eixos.text = veiculo.eixos

            val cargaMaxima = veiculo.eixos.toFloat() * 1.25
            carga.text =  cargaMaxima.toString()+ " ton"
        }
    }
}