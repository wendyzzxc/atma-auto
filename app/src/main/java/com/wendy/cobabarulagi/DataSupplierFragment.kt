package com.wendy.cobabarulagi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.wendy.cobabarulagi.databinding.FragmentDataSupplierBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

@Suppress("UNREACHABLE_CODE")
class DataSupplierFragment : Fragment() {
    private var _binding: FragmentDataSupplierBinding? = null
    private val binding get() = _binding!!
    private val listSupplier = ArrayList<SupplierData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDataSupplierBinding.inflate(inflater, container, false)
        getDataSupplier()
        return binding.root


    }
    override fun onStart() {
        super.onStart()
        getDataSupplier()
    }
    private fun getDataSupplier() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager= LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString("cari")
        binding.progressBar.visibility
        RClient.instances.getDatasupplier(cari).enqueue(object : Callback<ResponseDataSupplier> {
            override fun onResponse(
                call: Call<ResponseDataSupplier>,
                response: Response<ResponseDataSupplier>
            ){
                if (response.isSuccessful){
                    listSupplier.clear()
                    response.body()?.let {
                        listSupplier.addAll(it.data) }
                    val adapter =
                        SupplierAdapter(listSupplier, requireContext())
                    binding.rvData.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }
            override fun onFailure(call: Call<ResponseDataSupplier>, t:
            Throwable) {

            }
        }
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}