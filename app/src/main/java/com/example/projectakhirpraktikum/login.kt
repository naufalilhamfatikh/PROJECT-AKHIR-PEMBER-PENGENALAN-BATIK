package com.example.projectakhirpraktikum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.*

class login : AppCompatActivity() {

    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userDAO = UserDAO(this)
        userDAO.open()

        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val cbTampil = findViewById<CheckBox>(R.id.cbTampil)
        val button = findViewById<Button>(R.id.button)
        val buttonRegistrasi = findViewById<Button>(R.id.button2)

        cbTampil.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                edtPassword.transformationMethod = null // Menampilkan teks biasa
            } else {
                edtPassword.transformationMethod = PasswordTransformationMethod.getInstance() // Menyembunyikan teks password
            }
        }

        button.setOnClickListener {
            val usernameInput = edtUsername.text.toString()
            val passwordInput = edtPassword.text.toString()

            if (usernameInput.isBlank()) {
                Toast.makeText(this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (passwordInput.isBlank()) {
                Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                val user = userDAO.getUserByUsername(usernameInput)

                if (user != null) {
                    if (user.password == passwordInput) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                        // Menyimpan nilai jenis login setelah login berhasil
                        val sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("jenisLogin", "user")
                        editor.apply()

                        // Buka activity kedua
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("usernameUser", usernameInput)
                        intent.putExtra("passwordUser", passwordInput)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Invalid username", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonRegistrasi.setOnClickListener {
            val intent = Intent(this, Registrasi::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        userDAO.close()
    }
}
