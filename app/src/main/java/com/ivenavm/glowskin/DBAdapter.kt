package com.ivenavm.glowskin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DBAdapter(private val listDataku: ArrayList<DataModelGloskin>): RecyclerView.Adapter<DBAdapter.CardViewHolder>() {
    inner class CardViewHolder(itemV: View): RecyclerView.ViewHolder(itemV) {
        var tvnama: TextView = itemV.findViewById(R.id.tv_nama)
        var tvno: TextView = itemV.findViewById(R.id.tv_no)
        var tvkeluh: TextView = itemV.findViewById(R.id.tv_keluhan)
        var tvtreatment: TextView = itemV.findViewById(R.id.tv_treatment)
        var btndelete: Button = itemV.findViewById(R.id.btndelete)
        var btnupdate: Button = itemV.findViewById(R.id.btnupdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.itemdataku, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val dataku = listDataku[position]
        holder.tvnama.text = dataku.nama
        holder.tvno.text = dataku.no
        holder.tvkeluh.text = dataku.keluh
        holder.tvtreatment.text = dataku.treat

        holder.btndelete.setOnClickListener {
            var adapterDBHelper: DBHelper
            adapterDBHelper = DBHelper(holder.itemView.context)
            adapterDBHelper.deleteData(dataku.no)
            listDataku.removeAt(position)
            notifyDataSetChanged()
        }

        holder.btnupdate.setOnClickListener {
            val pindahUpdAc = Intent(holder.itemView.context, UpdateActivity::class.java)
            val bundle = Bundle()
            bundle.putString("nama", dataku.nama)
            bundle.putString("no", dataku.no)
            bundle.putString("keluhan", dataku.keluh)
            bundle.putString("treatment", dataku.treat)
            pindahUpdAc.putExtras(bundle)
            holder.itemView.context.startActivity(pindahUpdAc)
        }


    }

    override fun getItemCount(): Int {
        return listDataku.size
    }
}