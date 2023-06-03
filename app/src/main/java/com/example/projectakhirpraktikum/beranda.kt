package com.example.projectakhirpraktikum

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectakhirpraktikum.adapter_batik

class beranda : Fragment(), adapter_batik.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: adapter_batik
    private lateinit var listData: ArrayList<class_batik>
    private lateinit var originalList: ArrayList<class_batik>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_beranda, container, false)
        recyclerView = view.findViewById(R.id.idrecycle_beranda)
        listData = ArrayList()
        originalList = ArrayList()

        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        recyclerViewAdapter = adapter_batik(listData, this)

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return true
            }
        })

        searchView.setOnCloseListener {
            filterList("")
            false
        }

        recyclerView.adapter = recyclerViewAdapter
        originalList.add(class_batik(R.drawable.batik7rupa, "Batik Tujuh Rupa", "Asal Pekalongan"))
        originalList.add(class_batik(R.drawable.batiksogan, "Batik Sogan", "Asal Solo"))
        originalList.add(class_batik(R.drawable.batikgentongan, "Batik Gentongan", "Asal Madura"))
        originalList.add(class_batik(R.drawable.megamendung, "Batik Mega Mendung", "Asal Cirebon"))
        originalList.add(class_batik(R.drawable.batikkraton, "Batik Kraton", "Asal Yogyakarta"))
        originalList.add(class_batik(R.drawable.batiksimbut, "Batik Simbut", "Asal Banten"))
        originalList.add(class_batik(R.drawable.pringsedapur, "Batik Pring Sedapur", "Asal Magetan"))
        originalList.add(class_batik(R.drawable.parang, "Batik Parang", "Asal Pulau Jawa"))
        originalList.add(class_batik(R.drawable.geblekrenteng, "Batik Geblek Renteng", "Asal Kulon Progo"))
        originalList.add(class_batik(R.drawable.motifkawung, "Batik Kawung", "Asal Jawa Tengah"))

        // Inisialisasi listData dengan data asli
        listData.addAll(originalList)

        recyclerViewAdapter.notifyDataSetChanged() // notify data set changes
        return view
    }

    override fun onItemClick(position: Int) {
        val selectedBatik = listData[position]

        if (selectedBatik.nama == "Batik Sogan") {
            // Jika card Batik Sogan diklik, pindah ke activity BatikSoganActivity
            val intent = Intent(requireContext(), batik_sogan::class.java)
            startActivity(intent)
        } else if (selectedBatik.nama == "Batik Gentongan") {
            // Jika card Batik Tujuh Rupa diklik, pindah ke activity Batik Gentongan Activity
            val intent = Intent(requireContext(), batik_gantongan::class.java)
            startActivity(intent)
        } else if (selectedBatik.nama == "Batik Tujuh Rupa") {
            // Jika card Batik Tujuh Rupa diklik, pindah ke activity BatikTujuhRupaActivity
            val intent = Intent(requireContext(), batik_tujuhrupa::class.java)
            startActivity(intent)
        } else if (selectedBatik.nama == "Batik Mega Mendung") {
            // Jika card Batik Parang diklik, pindah ke activity Batik Batik Mega Mendung
            val intent = Intent(requireContext(), batik_MegaMendung::class.java)
            startActivity(intent)
        } else if (selectedBatik.nama == "Batik Kraton") {
            // Jika card Batik Parang diklik, pindah ke activity Batik Batik Kraton Activity
            val intent = Intent(requireContext(), batik_kraton::class.java)
            startActivity(intent)
        } else if (selectedBatik.nama == "Batik Simbut") {
            // Jika card Batik Parang diklik, pindah ke activity Batik Simbut Activity
            val intent = Intent(requireContext(), batik_simbut::class.java)
            startActivity(intent)
        } else if (selectedBatik.nama == "Batik Pring Sedapur") {
        // Jika card Batik Tujuh Rupa diklik, pindah ke activity Batik Pring Sedapur Activity
        val intent = Intent(requireContext(), batik_pringsedapur::class.java)
        startActivity(intent)
    } else if (selectedBatik.nama == "Batik Parang") {
        // Jika card Batik Parang diklik, pindah ke activity BatikParangActivity
        val intent = Intent(requireContext(), batik_parang::class.java)
        startActivity(intent)
    } else if (selectedBatik.nama == "Batik Geblek Renteng") {
        // Jika card Batik Parang diklik, pindah ke activity Batik Geblek Renteng Activity
        val intent = Intent(requireContext(), batik_geblekrenteng::class.java)
        startActivity(intent)
    } else if (selectedBatik.nama == "Batik Kawung") {
        // Jika card Batik Parang diklik, pindah ke activity Batik Geblek Renteng Activity
        val intent = Intent(requireContext(), batik_kawung::class.java)
        startActivity(intent)
    }


    }



    private fun filterList(text: String) {
        val filteredList = mutableListOf<class_batik>()
        if (text.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            for (batik in originalList) {
                if (batik.nama.toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(batik)
                }
            }
        }

        if (filteredList.isEmpty()) {
//            Toast.makeText(requireContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

        recyclerViewAdapter.updateList(filteredList)
    }
}
