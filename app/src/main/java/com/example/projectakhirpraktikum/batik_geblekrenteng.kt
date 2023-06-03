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

class batik_geblekrenteng : AppCompatActivity() {

    private lateinit var btnShare: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batik_geblekrenteng)

        btnShare = findViewById(R.id.btnShare_renteng)
        btnShare.setOnClickListener {
            shareImageAndText()
        }
    }

    private fun shareImageAndText() {
        // Mengambil gambar dari ImageView
        val imageView = findViewById<ImageView>(R.id.idfoto_renteng)
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
            Intent.EXTRA_TEXT, "*Batik Geblek Renteng*\n" +
                    "*Asal Kulon Progo*\n\n" +
                    "*Deskripsi:*\n" +
                    "Batik Geblek Renteng adalah salah satu varian batik yang memiliki ciri khas unik dan menarik. Motif ini berasal dari daerah Pekalongan, Jawa Tengah, Indonesia.\n\nGeblek Renteng menggabungkan elemen-elemen tradisional dengan nuansa modern yang menciptakan tampilan yang segar dan berbeda.\n\n"+
                    "Motif Batik Geblek Renteng ditandai dengan pola berulang yang terdiri dari garis-garis tipis dan melengkung yang saling bersilangan. Garis-garis ini membentuk persegi panjang dan menciptakan kesan simetris yang memukau. Warna yang dominan dalam Batik Geblek Renteng adalah warna-warna cerah seperti merah, biru, hijau, kuning, dan oranye yang memberikan sentuhan energik pada kain batik.\n\n"+
                    "Batik Geblek Renteng sering digunakan untuk membuat pakaian tradisional seperti kebaya, blus, atau kain panjang. Motifnya yang cerah dan dinamis membuatnya sangat populer dalam acara-acara perayaan, seperti pernikahan, acara budaya, atau festival. Selain itu, motif Batik Geblek Renteng juga digunakan dalam desain aksesori seperti syal, tas, atau sepatu untuk memberikan sentuhan batik pada gaya modern sehari-hari.\n\n"+
                    "Keunikan dari Batik Geblek Renteng tidak hanya terletak pada pola dan warnanya, tetapi juga pada proses pembuatannya. Batik ini dihasilkan melalui teknik lilin canting yang rumit, di mana seniman batik secara hati-hati menorehkan lilin pada kain dan kemudian mewarnainya dengan tangan. Proses ini membutuhkan keahlian dan ketelatenan yang tinggi, menjadikan setiap kain batik Geblek Renteng sebagai karya seni yang bernilai tinggi.\n\n"+
                    "Batik Geblek Renteng tidak hanya merupakan kain batik biasa, tetapi juga mengandung nilai-nilai budaya dan sejarah. Motif ini menggambarkan semangat hidup yang bersemangat, keceriaan, dan optimisme yang diwujudkan melalui pola warna yang cerah dan dinamis. Dalam konteks budaya Jawa, motif ini juga melambangkan kebahagiaan dan keselarasan dalam kehidupan.\n\n"+
                    "Secara keseluruhan, Batik Geblek Renteng adalah karya seni yang memukau, menggabungkan keindahan tradisional dengan sentuhan modern. Motifnya yang unik, warna yang cerah, dan proses pembuatannya yang rumit menjadikannya sebagai salah satu varian batik yang sangat dihargai dan diakui dalam dunia fashion dan seni."
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
