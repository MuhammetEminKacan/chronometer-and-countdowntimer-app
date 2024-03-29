package com.emin.kacan.kronometre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.emin.kacan.kronometre.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  butonlara basınca işlem yapmasını sağlamak için bottom navigation u fragment a bağladım
        val navHostFragment1 = supportFragmentManager.findFragmentById(R.id.navHostFragment1) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navHostFragment1.navController)
    }
}