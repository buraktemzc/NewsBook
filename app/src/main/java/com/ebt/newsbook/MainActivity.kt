package com.ebt.newsbook

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ebt.core.ui.extensions.observeInLifecycle
import com.ebt.features.detail_api.DetailNavigation
import com.ebt.features.home_api.HomeNavigation
import com.ebt.newsbook.databinding.ActivityMainBinding
import com.ebt.newsbook.model.Destination
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.ebt.core.ui.R as coreR

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var homeNavigation: HomeNavigation

    @Inject
    lateinit var detailNavigation: DetailNavigation

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                viewModel.goToHome()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(coreR.string.home)

        clearBackStack()
        homeNavigation.showHome(binding.container.id)
    }

    private fun clearBackStack() {
        while (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    private fun showDetail(rowId: Long) {
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(coreR.string.detail)

        detailNavigation.showDetail(binding.container.id, rowId)
    }
}