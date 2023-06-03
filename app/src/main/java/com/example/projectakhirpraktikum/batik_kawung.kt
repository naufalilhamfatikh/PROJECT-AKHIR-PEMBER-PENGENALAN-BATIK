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

class batik_kawung : AppCompatActivity() {

    private lateinit var btnShare: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batik_kawung)

        btnShare = findViewById(R.id.btnShare_kawung)
        btnShare.setOnClickListener {
            shareImageAndText()
        }
    }

    private fun shareImageAndText() {
        // Mengambil gambar dari ImageView
        val imageView = findViewById<ImageView>(R.id.idfoto_kawung)
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
            Intent.EXTRA_TEXT, "*Batik Kawung*\n" +
                    "*Asal Jawa Tengah*\n\n" +
                    "*Deskripsi:*\n" +
                    "Batik Kawung adalah salah satu motif batik yang sangat khas dan memiliki sejarah panjang di Indonesia. Motif ini dikenal sebagai salah satu motif batik tertua yang telah ada sejak zaman kerajaan Jawa. Motif Kawung terinspirasi dari bentuk buah kelapa muda yang berbentuk bundar dan memiliki empat sisi yang melengkung, menyerupai lingkaran kecil yang saling berhubungan.\n\n" +
                    "Motif Batik Kawung ditandai dengan pola-pola lingkaran kecil yang teratur dan simetris yang terbentuk dari empat buah kelapa muda yang saling berhubungan. Pola ini kemudian diisi dengan warna-warna yang kontras, seperti cokelat, hitam, biru, hijau, atau merah. Biasanya, warna-warna yang digunakan dalam Batik Kawung cenderung lebih netral, menciptakan kesan yang elegan dan klasik.\n\n" +
                    "Batik Kawung sering digunakan untuk membuat pakaian tradisional, seperti kebaya, blus, atau kain panjang. Motifnya yang sederhana dan elegan membuatnya sangat serbaguna dan cocok digunakan dalam berbagai kesempatan, baik formal maupun non-formal. Selain itu, motif Batik Kawung juga digunakan dalam desain aksesori seperti syal, selendang, atau tas untuk memberikan sentuhan budaya pada gaya modern.\n\n" +
                    "Kawung memiliki makna filosofis yang dalam dalam budaya Jawa. Pola lingkaran kecil yang berhubungan melambangkan hubungan manusia dengan alam semesta dan kehidupan yang saling terkait. Motif ini juga melambangkan kesatuan, keharmonisan, dan keindahan dalam kehidupan. Oleh karena itu, Batik Kawung sering digunakan dalam acara-acara pernikahan atau acara keagamaan, karena melambangkan kesatuan dan kerukunan dalam ikatan perkawinan atau kehidupan beragama.\n\n" +
                    "Batik Kawung adalah salah satu warisan budaya yang bernilai tinggi di Indonesia. Keindahan dan keunikannya telah diakui secara internasional dan menjadi sumber inspirasi bagi para perancang busana dan seniman. Kain batik dengan motif Kawung tidak hanya menjadi simbol keindahan estetika, tetapi juga mengandung nilai-nilai budaya dan sejarah yang menjadikannya sebagai salah satu simbol identitas bangsa Indonesia."
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
