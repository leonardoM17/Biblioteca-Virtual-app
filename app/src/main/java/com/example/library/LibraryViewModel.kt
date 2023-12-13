package com.example.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.library.data.Libro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibraryViewModel : ViewModel() {
    private val apiService = RetrofitClient.apiService

    fun fetchItems(catalogo: Int, tipo: String, palabra: String): MutableLiveData<List<Libro>> {
        val data = MutableLiveData<List<Libro>>()

        val call = apiService.getItems(catalogo, tipo, palabra)
        call.enqueue(object : Callback<List<Libro>> {
            override fun onResponse(call: Call<List<Libro>>, response: Response<List<Libro>>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Libro>>, t: Throwable) {
                // Maneja errores
            }
        })

        return data
    }
}
