package com.example.smashnews.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smashnews.R
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.databinding.ItemCategoryBinding
import com.inyongtisto.myhelper.extension.logs

class SubCategoryAdapter(var onClick: ((Int) -> Unit?)? = null) : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {

    private var data = ArrayList<Category>()
    private var indexSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemCategoryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item: List<Category>) {
        data.addAll(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelected(index: Int) {
        indexSelected = index
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val itemBinding: ItemCategoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(position: Int) {
            with(itemBinding) {
                val a = data[position]
                tvName.text = a.name
                val context = root.context
                if (indexSelected == adapterPosition) {
                    tvName.setBackgroundColor(context.getColor(R.color.grey5))
                    tvName.setTextColor(context.getColor(R.color.white))
                } else {
                    tvName.setBackgroundColor(context.getColor(R.color.grey1))
                    tvName.setTextColor(context.getColor(R.color.grey9))
                }

                lyMain.setOnClickListener {
                    onClick?.invoke(adapterPosition)
                    indexSelected = adapterPosition
                    notifyDataSetChanged()
                }
            }
        }
    }
}