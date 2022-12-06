package com.wendy.cobabarulagi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.wendy.cobabarulagi.databinding.ActivityDetailJasaServiceBinding
import com.wendy.cobabarulagi.databinding.ActivityDetailSupplierBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class DetailJasaServiceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailJasaServiceBinding
    private var b:Bundle? = null
    private val listJasaService = ArrayList<JasaServiceData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDetailJasaServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        b = intent.extras
        val nim = b?.getString("nim")
        nim?.let { getDataDetail(it) }
        binding.btnHapus.setOnClickListener {
            nim?.let { it1 -> deleteData(it1) }
        }
        binding.btnEdit.setOnClickListener {
            startActivity(
                Intent(this,
                    FormEditJasaServiceActivity::class.java).apply {
                    putExtra("nim",nim)
                })
        }
    }
    fun getDataDetail(ids:String){
        RClient.instances.getDatajasaService(ids).enqueue(object : Callback<ResponseDataJasaService> {
            override fun onResponse(
                call: Call<ResponseDataJasaService>,
                response: Response<ResponseDataJasaService>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listJasaService.addAll(it.data) }
                    with(binding) {
                        tvIds.text = listJasaService[0].nim
                        tvNama.text = listJasaService[0].nama
                        tvAlamat.text = listJasaService[0].harga
                    }
                }
            }
            override fun onFailure(call:
                                   Call<ResponseDataJasaService>, t: Throwable) {
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }
    fun deleteData(nim:String){
        val builder =
            AlertDialog.Builder(this@DetailJasaServiceActivity)
        builder.setMessage("Anda Yakin mau hapus?? Saya ngga yakin loh.")
            .setCancelable(false)
            .setPositiveButton("Ya, Hapus Aja!"){dialog, id -> doDeleteData(nim)
            }
            .setNegativeButton("Tidak, Masih sayang dataku"){dialog,id -> dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
    private fun doDeleteData(nim:String) {
        RClient.instances.deleteDatajasaService(nim).enqueue(object :
            Callback<ResponseCreate> {
            override fun onResponse(
                call: Call<ResponseCreate>,
                response: Response<ResponseCreate>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
            override fun onFailure(call: Call<ResponseCreate>, t:
            Throwable) {
            }
        })
    }
}