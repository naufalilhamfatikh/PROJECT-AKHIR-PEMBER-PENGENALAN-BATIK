// Registrasi.kt
package com.example.projectakhirpraktikum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registrasi : AppCompatActivity() {

    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)

        userDAO = UserDAO(this)
        userDAO.open()

        val addButton = findViewById<Button>(R.id.add)
        addButton.setOnClickListener {
            val editNama = findViewById<EditText>(R.id.edittext_nama)
            val editUmur = findViewById<EditText>(R.id.edittext_Umur)
            val editAlamat = findViewById<EditText>(R.id.edittext_Alamat)
            val editUsername = findViewById<EditText>(R.id.edittext_username)
            val editPassword = findViewById<EditText>(R.id.edittext_password)
            val editConfirmPassword = findViewById<EditText>(R.id.edittext_cinfir_pasword)

            val name = editNama.text.toString()
            val umur = editUmur.text.toString()
            val alamat = editAlamat.text.toString()
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()
            val confirmPassword = editConfirmPassword.text.toString()

            if (name.isEmpty() || umur.isEmpty() || alamat.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semuanya Harus Diisi", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Password tidak cocok", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(0, name, umur, alamat, username, password)
                userDAO.addUser(user)

                Toast.makeText(this, "Pengguna berhasil ditambahkan", Toast.LENGTH_SHORT).show()

                // Kembali ke LoginActivity setelah menambahkan pengguna
                val intent = Intent(this, login::class.java)
                startActivity(intent)
                finish()
            }
        }

        val buttonClose = findViewById<Button>(R.id.exit)
        buttonClose.setOnClickListener {
            // Kembali ke LoginActivity tanpa menambahkan pengguna
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        userDAO.close()
    }
}
