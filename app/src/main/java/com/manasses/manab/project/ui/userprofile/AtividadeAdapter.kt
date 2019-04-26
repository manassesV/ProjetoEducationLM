package com.manasses.manab.project.ui.userprofile

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.manasses.manab.project.R
import com.manasses.manab.project.data.local.entity.Atividade


class AtividadeAdapter(private val dataList: ArrayList<Atividade>,private val context: Context) :
    RecyclerView.Adapter<AtividadeAdapter.AtividadeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtividadeViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.fragment_atividade, parent, false)
        return AtividadeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AtividadeViewHolder, position: Int) {
        holder.txtAtividade!!.text = dataList[position].atividade!!
        holder.txtDescricao!!.text = dataList[position].descricao!!


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
     class AtividadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtAtividade: TextView
        var txtDescricao: TextView

        init {
            txtAtividade = itemView.findViewById(R.id.atividades)
            txtDescricao = itemView.findViewById(R.id.descricaos)
        }


    }
}
