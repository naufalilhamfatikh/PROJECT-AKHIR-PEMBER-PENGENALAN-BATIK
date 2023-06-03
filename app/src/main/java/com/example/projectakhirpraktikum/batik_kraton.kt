package com.example.projectakhirpraktikum

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileOutputStream

class batik_kraton : AppCompatActivity() {
    private lateinit var btnShare: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batik_kraton)

        btnShare = findViewById(R.id.btnSharekraton)
        btnShare.setOnClickListener {
            shareImageAndText()
        }
    }

    private fun shareImageAndText() {
        // Mengambil gambar dari ImageView
        val imageView = findViewById<ImageView>(R.id.idfoto_kraton)
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
        intent.putExtra(
            Intent.EXTRA_TEXT, "*Batik Kraton*\n" +
                    "*Asal Daerah Yogyakarrta*\n\n" +
                    "*Deskripsi:*\n" +
                    "Batik Kraton adalah salah satu jenis batik yang berasal dari Yogyakarta, Indonesia. Batik ini memiliki ciri khas yang kental dengan budaya dan tradisi kraton, yaitu istana kerajaan di Yogyakarta. \n" +
                    "Ciri khas Batik Kraton terletak pada motif dan warna yang digunakan. Motifnya biasanya terinspirasi oleh gambaran alam, flora, fauna, dan juga mitologi Jawa. Beberapa motif yang sering digunakan adalah motif parang, motif kawung, motif truntum, motif sida mukti, serta motif kembang sore. Motif-motif ini memiliki makna dan filosofi tersendiri dalam budaya Jawa"

        )

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

