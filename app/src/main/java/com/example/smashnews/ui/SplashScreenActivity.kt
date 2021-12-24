package com.example.smashnews.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import com.example.smashnews.core.data.source.remote.network.State
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.databinding.ActivityMainBinding
import com.example.smashnews.databinding.ActivitySplashScreenBinding
import com.example.smashnews.ui.adapter.MainSlidePageAdapter
import com.example.smashnews.ui.base.MyActivity
import com.example.smashnews.ui.main.MainActivity
import com.example.smashnews.ui.main.adapter.CategoryAdapter
import com.example.smashnews.ui.nav.NavActivity
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : MyActivity() {

    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivityMainDelay()
    }

    private fun startActivityMainDelay() {
        Handler(Looper.myLooper()!!).postDelayed({
            pushActivity(NavActivity::class.java)
        }, 1500)
    }
}