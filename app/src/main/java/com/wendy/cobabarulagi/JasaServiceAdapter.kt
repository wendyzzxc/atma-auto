package com.wendy.cobabarulagi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wendy.cobabarulagi.databinding.ListDataJasaServiceBinding
import java.util.ArrayList

class JasaServiceAdapter (
    private val listJasaService: ArrayList<JasaServiceData>,
    private val context: Context

): RecyclerView.Adapter<JasaServiceAdapter.JasaServiceViewHolder>() {
    inner class JasaServiceViewHolder(item: ListDataJasaServiceBinding): RecyclerView.ViewHolder(item.root){
        private val binding = item

        fun bind(JasaServiceData: JasaServiceData){
            with(binding) {
                txtNim.text = JasaServiceData.nim
                txtNama.text = JasaServiceData.nama
                cvData.setOnClickListener {
                    var i = Intent(context, DetailJasaServiceActivity::class.java).apply {
                        putExtra("nim",JasaServiceData.nim)
                    }
                    context.startActivity(i)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JasaServiceViewHolder {
        return JasaServiceViewHolder(ListDataJasaServiceBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }
    override fun onBindViewHolder(holder: JasaServiceViewHolder, position: Int) {
        holder.bind(listJasaService[position])
    }
    override fun getItemCount(): Int = listJasaService.size
}