package com.example.projectakhirpraktikum

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileOutputStream

class batik_sogan : AppCompatActivity() {

    private lateinit var btnShare: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batik_sogan)

        btnShare = findViewById(R.id.btnShare_sogan)
        btnShare.setOnClickListener {
            shareImageAndText()
        }
    }

    private fun shareImageAndText() {
        // Mengambil gambar dari ImageView
        val imageView = findViewById<ImageView>(R.id.idfoto_sagon)
        val bitmap = getBitmapFromImageView(imageView)

        // Menyimpan gambar ke file sementara
        val cachePath = File(cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "image.jpg")
        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.close()

        // Membuat Uri dari file sementara
        val imageUri = FileProvider.getUriForFile(this, "com.example.projectakhirpraktikum.fileprovider", file)

        // Membuat intent untuk berbagi
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)
        intent.putExtra(Intent.EXTRA_TEXT, "*Batik Sagon*\n" +
                "*Asal Daerah Solo*\n\n" +
                "*Deskripsi:*\n" +
                "Batik Sogan merupakan salah satu jenis batik bernuansa klasik. Dinamakan batik sogan karena pada awal mulanya, proses pewarnaan batik ini menggunakan pewarna alami yang diambil dari batang kayu pohon soga. Batik Sogan klasik merupakan jenis batik yang identik dengan daerah keraton Jawa yaitu Yogyakarta dan Solo, motifnya pun biasanya mengikuti pakem motif-motif klasik keraton. Sogan Yogya dan Solo juga dapat dibedakan dari warnanya. Biasanya sogan Yogya dominan berwarna coklat tua-kehitaman dan putih, sedangkan sogan Solo berwarna cokelat-oranye dan cokelat. Warna klasik batik Sogan sendiri sarat dengan makna. Ini dijelaskan dalam Serat Wirid Hidayat Jati, warna kekuningan keemasan merupakan bagian dari simbol keraton bangsa burung, bangsa makhluk penerbang, warna lokus dari perjalanan rohani setelah tersingkapnya alam Siriyah. Corak warna tersebut merupakan simbol-simbol yang telah dikenal sebelum hadirnya Islam di tanah Jawa, dan dalam perkembangannya kemudian diolah kembali oleh para Wali Songo.")

        // Mengatur flag untuk memberikan akses pada aplikasi lain untuk mengakses file
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        // Memulai aktivitas berbagi
        startActivity(Intent.createChooser(intent, "Bagikan gambar dan teks"))
    }

    private fun getBitmapFromImageView(imageView: ImageView): Bitmap {
        val drawable = imageView.drawable
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
