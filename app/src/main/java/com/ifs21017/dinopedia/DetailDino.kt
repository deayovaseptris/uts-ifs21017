package com.ifs21017.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Menu
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.ifs21017.dinopedia.databinding.DinoDetailBinding

class DetailDino : AppCompatActivity() {

    private lateinit var binding: DinoDetailBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DinoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO, Dino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "${dino!!.namadino}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_share -> {
                shareContent()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareContent() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareMessage = """
        Nama: ${dino?.namadino}
        Deskripsi: ${dino?.deskripsidino}
        Karakteristik: ${dino?.karakteristikdino}
        Kelompok: ${dino?.kelompok}
        Habitat: ${dino?.habitat}
        Makanan: ${dino?.makanan}
        Panjang: ${dino?.panjang}
        Tinggi: ${dino?.tinggi}
        Bobot Kelemahan: ${dino?.bobotkelemahan}
    """.trimIndent()
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Share Dinosaurus"))
    }

    private fun loadData(dino: Dino) {
        binding.ivGambar.setImageResource(dino.gambar)
        binding.tvNamaDino.text = dino.namadino
        binding.tvDeskripsiDino.text = dino.deskripsidino
        binding.tvKarakteristikDino.text = dino.karakteristikdino
        binding.tvKelompok.text = dino.kelompok
        binding.tvHabitat.text = dino.habitat
        binding.tvMakanan.text = dino.makanan
        binding.tvPanjang.text = dino.panjang
        binding.tvTinggi.text = dino.tinggi
        binding.tvBobotKelemahan.text = dino.bobotkelemahan
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}
