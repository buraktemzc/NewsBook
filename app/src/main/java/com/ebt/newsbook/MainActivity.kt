package com.ebt.newsbook

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ebt.core.ui.extensions.observeInLifecycle
import com.ebt.core.ui.extensions.show
import com.ebt.features.home_api.HomeNavigation
import com.ebt.newsbook.databinding.ActivityMainBinding
import com.ebt.newsbook.model.Destination
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var homeNavigation: HomeNavigation

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount == 0) {
                    finish()
                } else {
                    viewModel.goToHome()
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.goToLastScreen()
    }

    fun onNewsSelected(rowId: Long) {
        viewModel.onNewsSelected(rowId)
    }

    private fun observeData() {
        viewModel.destination.observeInLifecycle(this) {
            navigateToDestination(it)
        }
    }

    private fun navigateToDestination(destination: Destination) {
        when (destination) {
            is Destination.Home -> {
                showHome()
            }

            is Destination.Detail -> {
                showDetail(destination.rowId)
            }
        }
    }

    private fun showHome() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = "Home"
        binding.toolbar.show()

        clearBackStack()
        homeNavigation.showHome(binding.container.id)
    }

    private fun clearBackStack() {
        while (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    private fun showDetail(rowId: Long) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Title"
        binding.toolbar.show()

        // TODO: show detail
    }
}