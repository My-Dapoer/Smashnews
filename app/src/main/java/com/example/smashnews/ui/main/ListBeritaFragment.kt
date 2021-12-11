package com.example.smashnews.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.core.data.source.remote.network.State
import com.example.smashnews.databinding.FragmentListArticleBinding
import com.example.smashnews.ui.main.adapter.BeritaAdapter
import com.example.smashnews.ui.main.adapter.CategoryAdapter
import com.example.smashnews.ui.main.adapter.SubCategoryAdapter
import com.inyongtisto.myhelper.extension.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListBeritaFragment(private val category: Category) : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private var _binding: FragmentListArticleBinding? = null
    private val binding get() = _binding!!

//    private val viewModel: OpenTicketViewModel by viewModel()

    private val adapter = BeritaAdapter()
    private val adapterSubKategori = SubCategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListArticleBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvText.text = category.name
        setupAdapter()
        displaySubCategory()

        load()
    }

    private fun displaySubCategory() {
        binding.lyHorizontalScroll.visible(category.sub_categories.isNotEmpty())
        adapterSubKategori.addItem(category.sub_categories)
    }

    private fun setupAdapter() {
        // setup adapter berita
        binding.rvBerita.adapter = adapter

        // setup adapter categori
        binding.rvSubCategory.adapter = adapterSubKategori
    }

    private fun load() {
        viewModel.getBeritaByCategory(category.slug).observe(requireActivity(), {
            when (it.state) {
                State.SUCCESS -> {
                    adapter.addItem(it.data ?: emptyList())
                }
                State.ERROR -> {
                }
                State.LOADING -> {

                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(category: Category) = ListBeritaFragment(category)
    }
}