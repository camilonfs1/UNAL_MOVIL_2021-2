package com.unal.multiplayertictactoe.Game.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unal.webservices.BackEndCommunication.Entities.Item
import com.unal.webservices.BackEndCommunication.Entities.MunicipioItem
import com.unal.webservices.R

class AdapterRecyclerVertical(
    items_blog: ArrayList<MunicipioItem>
) : RecyclerView.Adapter<AdapterRecyclerVertical.ViewHolder>() {
    var items: ArrayList<MunicipioItem>? = null

    init {
        this.items = items_blog
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.vertical_item, parent, false)//create instance context
        var Holder = ViewHolder(vista)
        return Holder
    }

    override fun getItemCount(): Int {
        return this.items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.a?.text = item!!.municipio
        holder.b?.text = item.tipo_de_empresa
        holder.c?.text = item.entidad
        holder.d?.text = item.tiene_residuos_postconsumo
        holder.e?.text = item.ha_usado_aplicaciones
    }

    //ViewHolder inner class
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var vista = v
        var a: TextView? = null
        var b: TextView? = null
        var c: TextView? = null
        var d: TextView? = null
        var e: TextView? = null

        init {
            a = vista.findViewById(R.id.txt_a)
            b = vista.findViewById(R.id.txt_b)
            c = vista.findViewById(R.id.txt_c)
            d = vista.findViewById(R.id.txt_d)
            e = vista.findViewById(R.id.txt_e)
        }
    }
}