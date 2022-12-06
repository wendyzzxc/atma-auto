package com.wendy.cobabarulagi

import com.google.gson.annotations.SerializedName

data class SupplierData(
    @SerializedName("id") val nim:String,
    @SerializedName("nama_supplier") val nama:String,
    @SerializedName("alamat_supplier") val alamat:String,
    @SerializedName("nomor_supplier") val nomor:String
)