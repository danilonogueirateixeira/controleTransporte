package br.com.controletransporte.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.controletransporte.atualiza_empresa.AtualizarEmpresaActivity
import br.com.controletransporte.R
import br.com.controletransporte.model.Empresa
import kotlinx.android.synthetic.main.empresa_item.view.*
import br.com.controletransporte.detalhe_empresa.DetalheActivity
import br.com.controletransporte.lista_empresa.ListaEmpresaPresenterImpl
import java.io.Serializable


class EmpresaAdapter(private val empresas: MutableList<Empresa>,
                              private val context: Context
) : RecyclerView.Adapter<EmpresaAdapter.ViewHolder>() {

    val listaEmpresaPresenterImpl = ListaEmpresaPresenterImpl()

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.bindView(empresas[p1])

        p0.deletar.setOnClickListener {
            showAlert(p1)
        }

        p0.infor.setOnClickListener {
            val intent = Intent(context, DetalheActivity::class.java)
                intent.putExtra("empresa", empresas[p1] as Serializable)
                context.startActivity(intent)
        }

        p0.edit.setOnClickListener {
            val intent = Intent(context, AtualizarEmpresaActivity::class.java)
            intent.putExtra("empresa", empresas[p1] as Serializable)
            context.startActivity(intent)
            notifyItemChanged(p1)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.empresa_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return empresas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deletar = itemView.img_delete
        val infor = itemView.img_info
        val edit = itemView.img_edit

        fun bindView(empresa: Empresa) {
            val nome = itemView.txt_nome
            val segmento = itemView.txt_segmento
            val cep = itemView.txt_cep
            val estado = itemView.txt_estado
            val endereco = itemView.txt_endereco

            nome.text = empresa.nome
            segmento.text = empresa.segmento
            cep.text = empresa.cep
            estado.text = empresa.estado
            endereco.text = empresa.endereco
        }
    }

    private fun showAlert(position: Int) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(context.getString(R.string.excluir_empresa))
            .setMessage("Deseja realmente excluir o registro de "+empresas[position].nome+"?")
            .setPositiveButton(context.getString(R.string.excluir), DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
                listaEmpresaPresenterImpl.deletar(empresas[position])
                empresas.remove(empresas[position])
                notifyItemRemoved(position)
                notifyDataSetChanged()


            })
            .setNegativeButton( context.getString(R.string.cancelar), DialogInterface.OnClickListener { paramDialogInterface, paramInt -> })
        dialog.show()
    }
}