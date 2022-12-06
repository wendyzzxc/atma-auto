package com.wendy.cobabarulagi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.wendy.cobabarulagi.databinding.ActivityDetailPegawaiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class DetailPegawaiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailPegawaiBinding
    private var b:Bundle? = null
    private val listPegawai = ArrayList<PegawaiData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDetailPegawaiBinding.inflate(layoutInflater)
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
                    FormEditPegawaiActivity::class.java).apply {
                    putExtra("nim",nim)
                })
        }
    }
    fun getDataDetail(nim:String){
        RClient.instances.getData(nim).enqueue(object :
            Callback<ResponseDataPegawai> {
            override fun onResponse(
                call: Call<ResponseDataPegawai>,
                response: Response<ResponseDataPegawai>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listPegawai.addAll(it.data) }
                    with(binding) {
                        if(listPegawai[0].idr == "1"){
                            tvNim.text = "Owner"
                        }else if (listPegawai[0].idr == "2"){
                            tvNim.text = "Customer Service"
                        }else{
                            tvNim.text = "Kasir"
                        }

                        tvNama.text = listPegawai[0].nama
                        tvAlamat.text = listPegawai[0].alamat
                        tvProdi.text = listPegawai[0].prodi
                        tvTgllahir.text = listPegawai[0].tgllhr
                    }
                }
            }
            override fun onFailure(call:
                                   Call<ResponseDataPegawai>, t: Throwable) {
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }
    fun deleteData(nim:String){
        val builder =
            AlertDialog.Builder(this@DetailPegawaiActivity)
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
        RClient.instances.deleteData(nim).enqueue(object :
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