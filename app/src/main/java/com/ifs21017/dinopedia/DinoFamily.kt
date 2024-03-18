package com.ifs21017.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class DinoFamily(
    var namafamily: String,
    var deskripsi: String,
    var periodehidup: String,
    var karakterfisik: String,
    var habitatlingkungan: String,
    var perilakuklasifikasi: String,
) : Parcelable

