package com.wendy.cobabarulagi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wendy.cobabarulagi.databinding.ListDataSupplierBinding
import java.util.ArrayList

class SupplierAdapter (
    private val listSupplier: ArrayList<SupplierData>,
    private val context: Context

): RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>() {
    inner class SupplierViewHolder(item: ListDataSupplierBinding): RecyclerView.ViewHolder(item.root){
        private val binding = item

        fun bind(SupplierData: SupplierData){
            with(binding) {
                txtNim.text = SupplierData.nim
                txtNama.text = SupplierData.nama
                cvData.setOnClickListener {
                    var i = Intent(context, DetailSupplierActivity::class.java).apply {
                        putExtra("nim",SupplierData.nim)
                    }
                    context.startActivity(i)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        return SupplierViewHolder(ListDataSupplierBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }
    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        holder.bind(listSupplier[position])
    }
    override fun getItemCount(): Int = listSupplier.size
}