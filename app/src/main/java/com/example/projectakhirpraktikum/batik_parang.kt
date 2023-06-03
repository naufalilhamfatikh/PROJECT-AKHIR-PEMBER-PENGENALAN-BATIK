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

class batik_parang : AppCompatActivity() {

    private lateinit var btnShare: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batik_parang)

        btnShare = findViewById(R.id.btnShare_parang)
        btnShare.setOnClickListener {
            shareImageAndText()
        }
    }

    private fun shareImageAndText() {
        // Mengambil gambar dari ImageView
        val imageView = findViewById<ImageView>(R.id.idfoto_parang)
        val bitmap = getBitmapFromImageView(imageView)

        // Menyimpan gambar ke file sementara
        val cachePath = File(cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "image.png")
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
            Intent.EXTRA_TEXT, "*Batik Parang*\n" +
                    "*Asal Daerah Pulau Jawa*\n\n" +
                    "*Deskripsi:*\n" +
                    "Batik Parang adalah salah satu motif batik yang sangat terkenal dan memiliki makna mendalam di Indonesia. Motif Parang terinspirasi oleh senjata tradisional yang dikenal sebagai \"parang\" atau pedang, yang melambangkan keberanian, kekuatan, dan keberanian dalam budaya Jawa. Motif ini juga sering diartikan sebagai perlambang keseimbangan dan harmoni antara kehidupan manusia dengan alam semesta.\n\n" +
                    "Motif Batik Parang memiliki kekhasan dan keunikan yang membedakannya dari motif batik lainnya. Dengan garis-garis yang berulang dan simetri yang dihasilkan, Batik Parang menciptakan kesan tenang dan harmonis. Motif ini sering digunakan untuk membuat pakaian tradisional, seperti kebaya, sarung, atau kain panjang, serta aksesori seperti syal atau selendang.\n\n" +
                    "Selain keindahan visualnya, Batik Parang juga mengandung makna budaya yang dalam. Motif ini melambangkan semangat perjuangan, keberanian, dan keadilan, serta menggambarkan kehidupan yang seimbang dan harmonis. Oleh karena itu, Batik Parang sering digunakan dalam acara-acara penting, seperti upacara adat, pernikahan, atau perayaan keagamaan."
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
