package com.example.smashnews.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smashnews.core.data.source.remote.network.State
import com.example.smashnews.databinding.ActivityMainBinding
import com.example.smashnews.ui.base.MyActivity
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : MyActivity() {

    private val viewModel: MainViewModel by viewModel()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun getCategory() {
        viewModel.getCategory().observe(this, {

            when (it.state) {
                State.SUCCESS -> {

                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {

                }
            }
        })
    }
}