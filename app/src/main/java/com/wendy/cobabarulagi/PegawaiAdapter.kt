package com.wendy.cobabarulagi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wendy.cobabarulagi.databinding.ListDataPegawaiBinding
import java.util.ArrayList

class PegawaiAdapter (
    private val listPegawai: ArrayList<PegawaiData>,
    private val context: Context

): RecyclerView.Adapter<PegawaiAdapter.PegawaiViewHolder>() {
    inner class PegawaiViewHolder(item: ListDataPegawaiBinding): RecyclerView.ViewHolder(item.root){
        private val binding = item

        fun bind(PegawaiData: PegawaiData){
            with(binding) {
                txtNim.text = PegawaiData.nim
                txtNama.text = PegawaiData.nama
                cvData.setOnClickListener {
                    var i = Intent(context, DetailPegawaiActivity::class.java).apply {
                        putExtra("nim",PegawaiData.nim)
                    }
                    context.startActivity(i)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PegawaiViewHolder {
        return PegawaiViewHolder(ListDataPegawaiBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }
    override fun onBindViewHolder(holder: PegawaiViewHolder, position: Int) {
        holder.bind(listPegawai[position])
    }
    override fun getItemCount(): Int = listPegawai.size
}