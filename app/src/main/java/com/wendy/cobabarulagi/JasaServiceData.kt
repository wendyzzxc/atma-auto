package com.wendy.cobabarulagi

import com.google.gson.annotations.SerializedName

data class JasaServiceData(
    @SerializedName("id") val nim:String,
    @SerializedName("nama_jasa_service") val nama:String,
    @SerializedName("harga_jasa_service") val harga:String
)