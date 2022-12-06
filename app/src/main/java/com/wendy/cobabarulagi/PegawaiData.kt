package com.wendy.cobabarulagi

import com.google.gson.annotations.SerializedName

data class PegawaiData(
    @SerializedName("id") val nim:String,
    @SerializedName("id_role") val idr:String,
    @SerializedName("nama_pegawai") val nama:String,
    @SerializedName("alamat") val alamat:String,
    @SerializedName("no_telp") val prodi:String,
    @SerializedName("tanggal") val tgllhr:String,
)