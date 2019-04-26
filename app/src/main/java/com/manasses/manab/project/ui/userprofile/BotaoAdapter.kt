package com.manasses.manab.project.ui.userprofile

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.manasses.manab.project.R

class BotaoAdapter(private val dataList: ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<BotaoAdapter.BotaoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BotaoViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.fragment_botao, parent, false)
        return BotaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: BotaoViewHolder, position: Int) {
        holder.btDescricao!!.text = dataList[position]!!


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    class BotaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var btDescricao: Button

        init {
            btDescricao = itemView.findViewById(R.id.btDescricao)
        }


    }
}
