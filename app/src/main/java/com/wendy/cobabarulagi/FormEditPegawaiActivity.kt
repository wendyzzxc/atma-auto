package com.wendy.cobabarulagi

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wendy.cobabarulagi.databinding.ActivityFormEditPegawaiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FormEditPegawaiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditPegawaiBinding
    private var b:Bundle? = null
    private val listPegawai = ArrayList<PegawaiData>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFormEditPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Pegawai"
        b = intent.extras
        val nim = b?.getString("nim")
        binding.tvEditTgl.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view, year, month, dayofMonth -> binding.tglEditView.text = dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
        nim?.let { getDetailData(it) }
        binding.btnUpdate.setOnClickListener {
            with(binding) {
                val idr = txtNobp.text.toString()
                val nama = txtEditNama.text.toString()
                val alamat = txtEditAlamat.text.toString()
                val prodi = txtEditProdi.text.toString()
                val tgllahir = tglEditView.text.toString()

                RClient.instances.updateData(nim,idr,nama,alamat,prodi,tgllahir).enqueue(object :
                    Callback<ResponseCreate> {
                    override fun onResponse(
                        call: Call<ResponseCreate>,
                        response: Response<ResponseCreate>
                    ) {
                        if(response.isSuccessful) {

                            Toast.makeText(applicationContext,"${response.body()?.pesan}",
                                Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                    override fun onFailure(call:
                                           Call<ResponseCreate>, t: Throwable) {
                    }
                })
            }
        }
    }
    fun getDetailData(nim:String) {
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
                        txtNobp.setText(listPegawai[0].idr)

                        txtEditNama.setText(listPegawai[0].nama)

                        txtEditAlamat.setText(listPegawai[0].alamat)

                        txtEditProdi.setText(listPegawai[0].prodi)

                        tglEditView.setText(listPegawai[0].tgllhr)
                    }
                }
            }
            override fun onFailure(call:
                                   Call<ResponseDataPegawai>, t: Throwable) {
            }
        })
    }
    private fun dateToString(year: Int, month: Int, dayofMonth:
    Int): String {
        return year.toString()+"-"+(month+1)+"-"+dayofMonth.toString()
    }
    private fun dateDialog(context: Context, datePicker:
    DatePickerDialog.OnDateSetListener): DatePickerDialog {
        val calender = Calendar.getInstance()
        return DatePickerDialog(
            context, datePicker,
            calender[Calendar.YEAR],
            calender[Calendar.MONTH],
            calender[Calendar.DAY_OF_MONTH],
        )
    }
}