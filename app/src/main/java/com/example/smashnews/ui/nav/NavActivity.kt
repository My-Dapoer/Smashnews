package com.example.smashnews.ui.nav

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.smashnews.R
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.core.data.source.remote.network.State
import com.example.smashnews.databinding.ActivityNavBinding
import com.example.smashnews.ui.adapter.MainSlidePageAdapter
import com.example.smashnews.ui.main.MainViewModel
import com.example.smashnews.ui.main.adapter.CategoryAdapter
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavBinding

    private val adapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNav()
        setupAdapterCategory()
        setupViewPager()
        getCategory()
    }

    private fun setupViewPager() {
        binding.appBarNav.apply {
            mainViewPager.adapter = MainSlidePageAdapter(this@NavActivity)
            mainViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    rvKategori.post {
                        rvKategori.smoothScrollToPosition(position)
                    }
                    adapter.setSelected(position)
                }
            })
        }
    }

    private fun setupAdapterCategory() {
        binding.appBarNav.apply {
            rvKategori.adapter = adapter
            adapter.onClick = {
                mainViewPager.setCurrentItem(it, true)
            }
        }
    }

    private fun getCategory() {
        viewModel.getCategory().observe(this, {
            when (it.state) {
                State.SUCCESS -> {
                    adapter.clear()
                    displayData(it.data ?: emptyList(), "category")
                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {

                }
            }
        })
    }

    private fun getTags() {
        viewModel.getTags().observe(this, {
            when (it.state) {
                State.SUCCESS -> {
                    adapter.clear()
                    displayData(it.data ?: emptyList(), "tag")
                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {

                }
            }
        })
    }

    private fun displayData(list: List<Category>, type: String) {
        binding.appBarNav.mainViewPager.adapter = MainSlidePageAdapter(this, list, type)
        binding.appBarNav.mainViewPager.setCurrentItem(0, false)
        adapter.addItem(list)
    }

    private fun setupNav() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_nav)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_category, R.id.nav_tag, R.id.nav_informasi
            ), drawerLayout
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_category -> {
                    getCategory()
                }
                R.id.nav_tag -> {
                    getTags()
                }
                R.id.nav_informasi -> {}
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        binding.appBarNav.btnMenu.setOnClickListener {
            navController.navigateUp(appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_nav)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}