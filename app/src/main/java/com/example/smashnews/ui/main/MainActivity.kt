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

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}