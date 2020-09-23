package com.example.marvelapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.navigateUp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.character_list_dest, R.id.character_favorite_dest))

        setupActionBarConfiguration()
        setupBottomNavigationView()
        setupDestinationListener()
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp(appBarConfiguration)

    private fun setupDestinationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.character_detail_dest -> hideBottomNavigation()
                R.id.character_list_dest -> showBottomNavigation()
            }
        }
    }

    private fun hideBottomNavigation() {
        bottom_navigation_view.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        bottom_navigation_view.visibility = View.VISIBLE
    }

    private fun setupActionBarConfiguration() {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigationView() {
        bottom_navigation_view.setupWithNavController(navController)
    }
}