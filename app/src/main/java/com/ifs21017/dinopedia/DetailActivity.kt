package com.ifs21017.dinopedia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifs21017.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var dinoFamily: DinoFamily? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan data DinoFamily dari intent
        dinoFamily = intent.getParcelableExtra(EXTRA_DINOFAMILY)

        // Menampilkan tombol back di ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Jika data DinoFamily tidak null, maka tampilkan detailnya
        dinoFamily?.let {
            supportActionBar?.title = it.namafamily
            loadData(it)
        } ?: finish() // Jika data DinoFamily null, maka tutup activity

        // Mendapatkan referensi button
        val btnKlasifikasi = findViewById<Button>(R.id.btnKlasifikasi)

        // Menambahkan listener klik pada button
        btnKlasifikasi.setOnClickListener {
            // Pindah ke activity MainDino dengan membawa nama family sebagai data tambahan
            val intent = Intent(this@DetailActivity, MainDino::class.java)
            intent.putExtra(MainDino.EXTRA_SELECTED_FAMILY, dinoFamily?.namafamily)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_share -> {
                shareContent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareContent() {
        // Membuat intent untuk berbagi informasi tentang DinoFamily
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareMessage = """
            Nama Family: ${dinoFamily?.namafamily}
            Deskripsi: ${dinoFamily?.deskripsi}
            Periode Hidup: ${dinoFamily?.periodehidup}
            Karakteristik Fisik: ${dinoFamily?.karakterfisik}
            Habitat dan Lingkungan: ${dinoFamily?.habitatlingkungan}
            Perilaku dan Klasifikasi: ${dinoFamily?.habitatlingkungan}
            """.trimIndent()
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Share Dino Family"))
    }

    private fun loadData(dinoFamily: DinoFamily) {
        // Memuat data DinoFamily ke dalam tampilan
        binding.ivDetailNamafamily.text = dinoFamily.namafamily
        binding.tvDetailDeskripsi.text = dinoFamily.deskripsi
        binding.tvDetailPeriodehidup.text = dinoFamily.periodehidup
        binding.tvDetailKarakterfisik.text = dinoFamily.karakterfisik
        binding.tvDetailHabitatlingkungan.text = dinoFamily.habitatlingkungan
        binding.tvDetailPerilakuklasifikasi.text = dinoFamily.perilakuklasifikasi
    }

    companion object {
        const val EXTRA_DINOFAMILY = "EXTRA_DINOFAMILY"
    }
}
