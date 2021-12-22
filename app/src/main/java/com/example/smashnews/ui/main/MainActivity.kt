package com.example.smashnews.ui.main

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.smashnews.core.data.source.remote.network.State
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.databinding.ActivityMainBinding
import com.example.smashnews.ui.adapter.MainSlidePageAdapter
import com.example.smashnews.ui.base.MyActivity
import com.example.smashnews.ui.main.adapter.CategoryAdapter
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : MyActivity() {

    private val viewModel: MainViewModel by viewModel()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupAdapterCategory()
//        setupViewPager()
//        getCategory()
    }

//    private fun setupViewPager() {
//        binding.appBarNav.apply {
//            mainViewPager.adapter = MainSlidePageAdapter(this@NavActivity)
//            mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    rvKategori.post {
//                        rvKategori.smoothScrollToPosition(position)
//                    }
//                    adapter.setSelected(position)
//                }
//            })
//        }
//    }
//
//    private fun setupAdapterCategory() {
//        binding.appBarNav.apply {
//            rvKategori.adapter = adapter
//            adapter.onClick = {
//                mainViewPager.setCurrentItem(it, true)
//            }
//        }
//    }
//
//    private fun getCategory() {
//        viewModel.getCategory().observe(this, {
//            when (it.state) {
//                State.SUCCESS -> {
//                    displayData(it.data ?: emptyList(), "category")
//                }
//                State.ERROR -> {
//                    toastError(it.message ?: "Error")
//                }
//                State.LOADING -> {
//
//                }
//            }
//        })
//    }
//
//    private fun getTags() {
//        viewModel.getTags().observe(this, {
//            when (it.state) {
//                State.SUCCESS -> {
//                    displayData(it.data ?: emptyList(), "tag")
//                }
//                State.ERROR -> {
//                    toastError(it.message ?: "Error")
//                }
//                State.LOADING -> {
//
//                }
//            }
//        })
//    }
//
//    private fun displayData(list: List<Category>, type: String) {
//        binding.appBarNav.mainViewPager.adapter = MainSlidePageAdapter(this, list, type)
//        binding.appBarNav.mainViewPager.setCurrentItem(0, false)
//        adapter.addItem(list)
//    }

}