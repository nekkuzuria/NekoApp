package com.example.nekoapp

import NekoAdapter
import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nekoapp.databinding.ActivityMainBinding
import com.example.nekoapp.model.Users
import com.example.nekoapp.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy {  //dijalankan oleh sistem ketika dipanggil
        ActivityMainBinding.inflate(layoutInflater) //klo ga dipanggil, fungsi di dalam tidak digunakan
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//      Mengambil data API
        val client = ApiClient.getInstance()
        val response = client.getAllUser()

        val namaAuthor = ArrayList<String>()
        val url = ArrayList<String>()
        val author_href = ArrayList<String>()

        response.enqueue(object : retrofit2.Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>){
                for (i in response.body()?.results?: arrayListOf()){
                    namaAuthor.add(i.artistName)
                    url.add(i.url)
                    author_href.add(i.artistHref)
                }

//                Apply ke Recycle View
                val nekoList = mutableListOf<Neko>()
                for (i in 0 until namaAuthor.size) {
                    val neko = Neko(
                        namaAuthor = namaAuthor[i],
                        url = url[i],
                        author_href = author_href[i]
                    )
                    nekoList.add(neko)
                }




                val adapterNeko = NekoAdapter(nekoList)
                with(binding){
                    revNeko.apply {
                        adapter = adapterNeko
                        layoutManager = LinearLayoutManager(this@MainActivity)

                    }
                }

            }
            override fun onFailure(call: Call<Users>, t:Throwable){
                Toast.makeText(this@MainActivity, "Koneksi error", Toast.LENGTH_SHORT).show()
            }
        })


    }
}