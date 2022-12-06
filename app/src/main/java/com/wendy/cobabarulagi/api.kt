package com.wendy.cobabarulagi

import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("pegawaiController/{cari}")
    fun getData(@Path("cari") cari:String? = null):
            Call<ResponseDataPegawai>
    @FormUrlEncoded
    @POST("pegawaiController")
    fun createData(
        @Field("id_role") id_role:String?,
        @Field("nama_pegawai") nama:String?,
        @Field("alamat") alamat:String?,
        @Field("no_telp") no_telp:String?,
        @Field("tanggal") tanggal:String?,
    ): Call<ResponseCreate>
    @DELETE("pegawaiController/{nim}")
    fun deleteData(@Path("nim")nim: String?): Call<ResponseCreate>
    @FormUrlEncoded
    @PUT("pegawaiController/{nim}")
    fun updateData(
        @Path("nim") nim:String?,
        @Field("id_role") id_role:String?,
        @Field("nama_pegawai") nama:String?,
        @Field("alamat") alamat:String?,
        @Field("no_telp") no_telp:String?,
        @Field("tanggal") tanggal:String?,
    ): Call<ResponseCreate>


    //supplier
    @GET("supplierController/{cari}")
    fun getDatasupplier(@Path("cari") cari:String? = null):
            Call<ResponseDataSupplier>
    @FormUrlEncoded
    @POST("supplierController")
    fun createDatasupplier(
        @Field("nama_supplier") nama:String?,
        @Field("alamat_supplier") alamat:String?,
        @Field("nomor_supplier") nomor:String?
    ): Call<ResponseCreate>
    @DELETE("supplierController/{nim}")
    fun deleteDataSupplier(@Path("nim")nim: String?): Call<ResponseCreate>
    @FormUrlEncoded
    @PUT("supplierController/{nim}")
    fun updateDataSupplier(
        @Path("nim") nim:String?,
        @Field("nama_supplier") nama:String?,
        @Field("alamat_supplier") alamat:String?,
        @Field("nomor_supplier") nomor:String?
    ): Call<ResponseCreate>



    //jasa service
    @GET("jasaServiceController/{cari}")
    fun getDatajasaService(@Path("cari") cari:String? = null):
            Call<ResponseDataJasaService>
    @FormUrlEncoded
    @POST("jasaServiceController")
    fun createDatajasaService(
        @Field("nama_jasa_service") nama:String?,
        @Field("harga_jasa_service") harga:String?
    ): Call<ResponseCreate>
    @DELETE("jasaServiceController/{nim}")
    fun deleteDatajasaService(@Path("nim")nim: String?): Call<ResponseCreate>
    @FormUrlEncoded
    @PUT("jasaServiceController/{nim}")
    fun updateDatajasaService(
        @Path("nim") nim:String?,
        @Field("nama_jasa_service") nama:String?,
        @Field("harga_jasa_service") harga:String?
    ): Call<ResponseCreate>
}