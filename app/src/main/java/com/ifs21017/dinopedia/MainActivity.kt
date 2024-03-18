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
import com.ifs21017.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataDinoFamily = ArrayList<DinoFamily>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDinoFamily.setHasFixedSize(false)
        dataDinoFamily.addAll(getListDinoFamily())
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
    private fun getListDinoFamily(): ArrayList<DinoFamily> {
        val dataNamafamily =
            resources.getStringArray(R.array.familydino_namafamily)
        val dataDeskripsi =
            resources.getStringArray(R.array.familydino_deskripsi)
        val dataPeriodehidup =
            resources.getStringArray(R.array.familydino_periodehidup)
        val dataKarakterfisik =
            resources.getStringArray(R.array.familydino_karakterfisik)
        val dataHabitatlingkungan =
            resources.getStringArray(R.array.familydino_habitatlingkungan)
        val dataPerilakuklasifikasi =
            resources.getStringArray(R.array.familydino_perilakuklasifikasi)
        val listDinoFamily = ArrayList<DinoFamily>()
        for (i in dataNamafamily.indices) {
            val dinoFamily = DinoFamily(dataNamafamily[i],
                dataDeskripsi[i], dataPeriodehidup[i],
                dataKarakterfisik[i], dataHabitatlingkungan[i], dataPerilakuklasifikasi[i])
            listDinoFamily.add(dinoFamily)
        }
        return listDinoFamily
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinoFamily.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinoFamily.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoFamilyAdapter = DinoFamilyAdapter(dataDinoFamily)
        binding.rvDinoFamily.adapter = listDinoFamilyAdapter
        listDinoFamilyAdapter.setOnItemClickCallback(object :
            DinoFamilyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DinoFamily) {
                showSelectedDinoFamily(data)
            }
        })
    }

    private fun showSelectedDinoFamily(dinoFamily: DinoFamily) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_DINOFAMILY, dinoFamily)
        startActivity(intentWithData)
    }
}