package com.karimali.movieverse

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.karimali.movieverse.core.common.utils.Constants
import com.karimali.movieverse.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enables edge-to-edge display, allowing content to flow under system bars
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        setupWindowInsets()

        initNavController()

        setupToolBar()

        setupBottomNavBar()

        setupNavigationVisibility()
    }

    private fun setupNavigationVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navBottomView.isVisible = destination.id in Constants.topLevelDestinations
            binding.toolBar.isVisible = destination.id !in Constants.destinationsWithoutToolBar
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupToolBar() {
        // setup with toolbar
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = Constants.topLevelDestinations,
        )
        setSupportActionBar(binding.toolBar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavBar() {
        setupWithNavController(binding.navBottomView, navController)
    }

    private fun setupWindowInsets() {
        // Handle window insets to ensure UI elements aren't obscured by system bars (status/navigation bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Apply padding to the root view to respect system bar boundaries
            insets
        }
    }

    private fun initNavController() {
        // Retrieve the NavHostFragment which contains the navigation graph
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as? NavHostFragment ?: return

        // Initialize the NavController from the NavHostFragment
        navController = navHostFragment.navController
    }
}