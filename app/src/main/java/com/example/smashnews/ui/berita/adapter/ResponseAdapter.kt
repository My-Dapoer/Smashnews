package com.example.smashnews.ui.berita.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smashnews.R
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.core.data.source.remote.model.Response
import com.example.smashnews.databinding.ItemCategoryBinding
import com.example.smashnews.databinding.ItemResponseBinding
import com.inyongtisto.myhelper.extension.logs
import com.squareup.picasso.Picasso

class ResponseAdapter(var onClick: ((Response) -> Unit?)? = null) :
    RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {

    private var data = ArrayList<Response>()
    private var indexSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemResponseBinding.inflate(
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
    fun addItem(item: List<Response>) {
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

    inner class ViewHolder(private val itemBinding: ItemResponseBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(position: Int) {
            with(itemBinding) {
                val a = data[position]
                tvName.text = a.percentage
                Picasso.get().load(a.url_image).into(imageView)

                lyMain.setOnClickListener {
                    onClick?.invoke(a)
                }
            }
        }
    }
}