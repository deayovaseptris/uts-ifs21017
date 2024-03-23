package com.ifs21017.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dino(
    var namadino: String,
    var gambar: Int,
    var deskripsidino: String,
    var karakteristikdino: String,
    var kelompok: String,
    var habitat: String,
    var makanan: String,
    var panjang: String,
    var tinggi: String,
    var bobotkelemahan: String,
) : Parcelable
