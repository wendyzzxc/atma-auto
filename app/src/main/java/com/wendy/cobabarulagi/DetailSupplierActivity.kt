package com.wendy.cobabarulagi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.wendy.cobabarulagi.databinding.ActivityDetailPegawaiBinding
import com.wendy.cobabarulagi.databinding.ActivityDetailSupplierBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class DetailSupplierActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailSupplierBinding
    private var b:Bundle? = null
    private val listSupplier = ArrayList<SupplierData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDetailSupplierBinding.inflate(layoutInflater)
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
                    FormEditSupplierActivity::class.java).apply {
                    putExtra("nim",nim)
                })
        }
    }
    fun getDataDetail(ids:String){
        RClient.instances.getDatasupplier(ids).enqueue(object : Callback<ResponseDataSupplier> {
            override fun onResponse(
                call: Call<ResponseDataSupplier>,
                response: Response<ResponseDataSupplier>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listSupplier.addAll(it.data) }
                    with(binding) {
                        tvIds.text = listSupplier[0].nim
                        tvNama.text = listSupplier[0].nama
                        tvAlamat.text = listSupplier[0].alamat
                        tvProdi.text = listSupplier[0].nomor
                    }
                }
            }
            override fun onFailure(call:
                                   Call<ResponseDataSupplier>, t: Throwable) {
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }
    fun deleteData(nim:String){
        val builder =
            AlertDialog.Builder(this@DetailSupplierActivity)
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
        RClient.instances.deleteDataSupplier(nim).enqueue(object :
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