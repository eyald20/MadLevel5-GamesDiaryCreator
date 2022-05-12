package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment

        navController = navHostFragment.navController


//        val navController = findNavController(R.id.action_AddGameFragment_to_GamesFragment)
        val config = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, config)


//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.plusFab.setOnClickListener { view ->
            navController.navigate(
                R.id.action_GamesFragment_to_AddGameFragment
            )
        }

        fabToggler()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fabToggler() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.AddGameFragment)) {
                binding.plusFab.hide()
            } else {
                binding.plusFab.show()
            }
        }
    }
}