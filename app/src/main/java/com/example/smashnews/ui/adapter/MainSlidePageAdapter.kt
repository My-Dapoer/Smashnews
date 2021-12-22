package com.example.smashnews.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.ui.main.ListBeritaFragment

class MainSlidePageAdapter(
    fa: FragmentActivity,
    private val list: List<Category> = emptyList(),
    val type: String = "category"
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        return ListBeritaFragment.newInstance(list[position], type)
    }
}