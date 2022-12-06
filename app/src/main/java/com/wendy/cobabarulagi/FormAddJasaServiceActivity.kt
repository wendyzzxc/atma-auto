package com.wendy.cobabarulagi

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wendy.cobabarulagi.databinding.ActivityFormAddJasaServiceBinding
import com.wendy.cobabarulagi.databinding.ActivityFormAddSupplierBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FormAddJasaServiceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormAddJasaServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormAddJasaServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            saveData()
        }
    }

    fun saveData(){
        with(binding) {
            val nama= txtNama.text.toString()
            val alamat = txtAlamat.text.toString()

            RClient.instances.createDatajasaService(nama,alamat).enqueue(object :
                Callback<ResponseCreate> {
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    if(response.isSuccessful){

                        Toast.makeText(applicationContext,"${response.body()?.pesan}",
                            Toast.LENGTH_LONG).show()
                        finish()
                    }else {
                    }
                }
                override fun onFailure(call:
                                       Call<ResponseCreate>, t: Throwable) {
                }
            })
        }
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