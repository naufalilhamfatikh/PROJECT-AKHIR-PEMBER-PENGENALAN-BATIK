package com.example.projectakhirpraktikum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.Beranda -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, beranda()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Akun -> {
                    val username = intent.getStringExtra("usernameUser")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, akun.newInstance(username))
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Kelompok -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, kelompok()).commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction().replace(R.id.frame_container, beranda())
            .commit()
    }
}
