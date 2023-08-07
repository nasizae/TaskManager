package com.example.taskmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.data.local.Pref
import com.example.taskmeneger.R
import com.example.taskmeneger.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pref: Pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!pref.isOnBoardingShow())
            navController.navigate(R.id.navigation_onBoarding)

        if (FirebaseAuth.getInstance().currentUser?.uid == null) {
            navController.navigate(R.id.registrationFragment)
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.taskFragment,
                R.id.navigation_onBoarding,
                R.id.registrationFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val fragmentWithOutBottomNav = setOf(
            R.id.navigation_onBoarding,
            R.id.phoneFragment,
            R.id.verifyFragment,
            R.id.registrationFragment,
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (fragmentWithOutBottomNav.contains(destination.id)) {
                navView.isVisible = false
                supportActionBar?.hide()
            } else {
                navView.isVisible = true
                supportActionBar?.show()
            }
        }

    }
}