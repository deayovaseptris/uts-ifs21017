package com.ifs21017.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21017.dinopedia.databinding.DinoMainBinding

class MainDino : AppCompatActivity() {

    private lateinit var binding: DinoMainBinding
    private val dataDino = ArrayList<Dino>()
    private var selectedFamily: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DinoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDino.setHasFixedSize(false)
        //filter
        selectedFamily = intent.getStringExtra(EXTRA_SELECTED_FAMILY)

        selectedFamily?.let {
            dataDino.addAll(getListDinoByFamily(it))
        }

        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val dataNamadino = resources.getStringArray(R.array.dino_namadino)
        val dataGambardino = resources.obtainTypedArray(R.array.dino_gambardino)
        val dataDeskripsidino = resources.getStringArray(R.array.dino_deskripsidino)
        val dataKarakteristikdino = resources.getStringArray(R.array.dino_karakteristikdino)
        val dataKelompok = resources.getStringArray(R.array.dino_kelompok)
        val dataHabitat = resources.getStringArray(R.array.dino_habitat)
        val dataMakanan = resources.getStringArray(R.array.dino_makanan)
        val dataPanjang = resources.getStringArray(R.array.dino_panjang)
        val dataTinggi = resources.getStringArray(R.array.dino_tinggi)
        val dataBobotkelemahan = resources.getStringArray(R.array.dino_bobotkelemahan)

        val listDino = ArrayList<Dino>()
        for (i in dataNamadino.indices) {
            val dino = Dino(
                dataNamadino[i],
                dataGambardino.getResourceId(i, -1),
                dataDeskripsidino[i],
                dataKarakteristikdino[i],
                dataKelompok[i],
                dataHabitat[i],
                dataMakanan[i],
                dataPanjang[i],
                dataTinggi[i],
                dataBobotkelemahan[i]
            )
            listDino.add(dino)
        }
        return listDino
    }

    //filter
    @SuppressLint("Recycle")
    private fun getListDinoByFamily(family: String): ArrayList<Dino> {
        val allDino = getListDino()
        val filteredDino = ArrayList<Dino>()
        for (dino in allDino) {
            if (dino.kelompok == family) {
                filteredDino.add(dino)
            }
        }
        return filteredDino
    }

    //ditampilkan
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDino.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoAdapter = DinoAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            DinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@MainDino,
            DetailDino::class.java)
        intentWithData.putExtra(DetailDino.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }

    companion object {
        const val EXTRA_SELECTED_FAMILY = "EXTRA_SELECTED_FAMILY"
    }
}
