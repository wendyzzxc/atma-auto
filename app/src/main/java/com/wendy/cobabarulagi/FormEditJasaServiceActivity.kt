package com.wendy.cobabarulagi

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wendy.cobabarulagi.databinding.ActivityFormEditJasaServiceBinding
import com.wendy.cobabarulagi.databinding.ActivityFormEditSupplierBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FormEditJasaServiceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditJasaServiceBinding
    private var b:Bundle? = null
    private val listJasaService = ArrayList<JasaServiceData>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFormEditJasaServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Jasa Service"
        b = intent.extras
        val nim = b?.getString("nim")
        nim?.let { getDetailData(it) }
        binding.btnUpdate.setOnClickListener {
            with(binding) {
                val nama = txtEditNama.text.toString()
                val alamat = txtEditAlamat.text.toString()

                RClient.instances.updateDatajasaService(nim,nama,alamat).enqueue(object :
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
        RClient.instances.getDatajasaService(nim).enqueue(object : Callback<ResponseDataJasaService> {
            override fun onResponse(
                call: Call<ResponseDataJasaService>,
                response: Response<ResponseDataJasaService>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listJasaService.addAll(it.data) }
                    with(binding) {

                        txtEditNama.setText(listJasaService[0].nama)

                        txtEditAlamat.setText(listJasaService[0].harga)

                    }
                }
            }
            override fun onFailure(call:
                                   Call<ResponseDataJasaService>, t: Throwable) {
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