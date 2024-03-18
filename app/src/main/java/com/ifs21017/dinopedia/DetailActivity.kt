package com.ifs21017.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Menu
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ifs21017.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var dinoFamily: DinoFamily? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dinoFamily = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINOFAMILY, DinoFamily::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINOFAMILY)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dinoFamily != null) {
            supportActionBar?.title = "${dinoFamily!!.namafamily}"
            loadData(dinoFamily!!)
        } else {
            finish()
        }

        val btnLogin = findViewById<Button>(R.id.btnKlasifikasi)

        btnLogin.setOnClickListener {
            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
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
        binding.ivDetailNamafamily.text = dinoFamily.namafamily
        binding.tvDetailDeskripsi.text = dinoFamily.deskripsi
        binding.tvDetailPeriodehidup.text = dinoFamily.periodehidup
        binding.tvDetailKarakterfisik.text = dinoFamily.karakterfisik
        binding.tvDetailHabitatlingkungan.text = dinoFamily.habitatlingkungan
        binding.tvDetailPerilakuklasifikasi.text = dinoFamily.perilakuklasifikasi
    }

    companion object {
        const val EXTRA_DINOFAMILY = "extra_dinofamily"
    }
}
