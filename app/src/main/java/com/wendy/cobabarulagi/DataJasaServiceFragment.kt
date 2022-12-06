package com.wendy.cobabarulagi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.wendy.cobabarulagi.databinding.FragmentDataJasaServiceBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

@Suppress("UNREACHABLE_CODE")
class DataJasaServiceFragment : Fragment() {
    private var _binding: FragmentDataJasaServiceBinding? = null
    private val binding get() = _binding!!
    private val listJasaService = ArrayList<JasaServiceData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDataJasaServiceBinding.inflate(inflater, container, false)
        getDataJasaService()
        return binding.root


    }
    override fun onStart() {
        super.onStart()
        getDataJasaService()
    }
    private fun getDataJasaService() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager= LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString("cari")
        binding.progressBar.visibility
        RClient.instances.getDatajasaService(cari).enqueue(object : Callback<ResponseDataJasaService> {
            override fun onResponse(
                call: Call<ResponseDataJasaService>,
                response: Response<ResponseDataJasaService>
            ){
                if (response.isSuccessful){
                    listJasaService.clear()
                    response.body()?.let {
                        listJasaService.addAll(it.data) }
                    val adapter =
                        JasaServiceAdapter(listJasaService, requireContext())
                    binding.rvData.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }
            override fun onFailure(call: Call<ResponseDataJasaService>, t:
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