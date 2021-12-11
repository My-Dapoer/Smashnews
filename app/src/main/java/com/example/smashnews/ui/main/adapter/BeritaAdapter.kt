package com.example.smashnews.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smashnews.core.data.source.remote.model.Berita
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.databinding.ItemBeritaBinding
import com.inyongtisto.myhelper.extension.convertTanggal

class BeritaAdapter(var onClick: ((data: Berita) -> Unit?)? = null) : RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {

    var data = ArrayList<Berita>()

    lateinit var contex: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contex = parent.context
        return ViewHolder(
                ItemBeritaBinding.inflate(
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
    fun addItem(item: List<Berita>) {
        data.addAll(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val itemBinding: ItemBeritaBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            with(itemBinding) {
                val a = data[position]
                val formatBaru = "dd MMM yyyy"
                tvName.text = a.name
                tvTanggal.text = a.publish_at.convertTanggal(a.publish_at, formatBaru)
            }
        }
    }
}