package com.wendy.cobabarulagi

import com.google.gson.annotations.SerializedName

data class ResponseDataPegawai(
    @SerializedName("status") val stt:String,
    val totaldata: Int,
    val data: List<PegawaiData>
)