package com.java90.noticashilt.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.java90.noticashilt.R
import com.java90.noticashilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        //Set the first fragment without back_press
        val appBarConfiguration = AppBarConfiguration
                .Builder(R.id.listNotesFragment)
                .build()

        /**
         *  Set Visibility of toolbar
         */
        /*
        navController.addOnDestinationChangedListener {_, destination,_ ->
            when(destination.id) {
                R.id.layout,
                R.id.layout,
                R.id.layout,
                R.id.layout -> {
                    binding.toolbar.visibility = View.GONE
                }
                else -> binding.toolbar.visibility = View.VISIBLE
            }
        }
         */
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onResume() {
        handleBackPress()
        super.onResume()
    }

    // Set the back_press button navigation
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun handleBackPress() = this.onBackPressedDispatcher.addCallback(object :
    OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navController.currentDestination?.let { current->
                when(current.id) {
                    R.id.listNotesFragment -> finish()
                    else -> navController.navigateUp()
                }
            }
        }
    })
}