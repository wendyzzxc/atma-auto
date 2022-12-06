package com.wendy.cobabarulagi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.wendy.cobabarulagi.databinding.FragmentDataPegawaiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

@Suppress("UNREACHABLE_CODE")
class DataPegawaiFragment : Fragment() {
    private var _binding: FragmentDataPegawaiBinding? = null
    private val binding get() = _binding!!
    private val listPegawai = ArrayList<PegawaiData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDataPegawaiBinding.inflate(inflater, container, false)
        getDataPegawai()
        return binding.root


    }
    override fun onStart() {
        super.onStart()
        getDataPegawai()
    }
    private fun getDataPegawai() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager= LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString("cari")
        binding.progressBar.visibility
        RClient.instances.getData(cari).enqueue(object :
            Callback<ResponseDataPegawai> {
            override fun onResponse(
                call: Call<ResponseDataPegawai>,
                response: Response<ResponseDataPegawai>
            ){
                if (response.isSuccessful){
                    listPegawai.clear()
                    response.body()?.let {
                        listPegawai.addAll(it.data) }
                    val adapter =
                        PegawaiAdapter(listPegawai, requireContext())
                    binding.rvData.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }
            override fun onFailure(call: Call<ResponseDataPegawai>, t:
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