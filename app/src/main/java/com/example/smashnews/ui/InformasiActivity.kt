package com.example.smashnews.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import com.example.smashnews.core.data.source.remote.model.Berita
import com.example.smashnews.core.data.source.remote.network.State
import com.example.smashnews.databinding.ActivityInfoBinding
import com.example.smashnews.ui.base.MyActivity
import com.example.smashnews.ui.berita.adapter.ResponseAdapter
import com.example.smashnews.ui.main.MainViewModel
import com.example.smashnews.ui.main.adapter.BeritaAdapter
import com.example.smashnews.util.Constants
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.smashnews.util.base64Handler.URLImageParser


class InformasiActivity : MyActivity() {

    private val viewModel: MainViewModel by viewModel()

    private var _binding: ActivityInfoBinding? = null
    private val binding get() = _binding!!
    private var berita = Berita()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Informasi")

        getIntentExtra()
    }

    @SuppressLint("SetTextI18n")
    private fun getIntentExtra() {
        getDetailInfo()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        binding.apply {
            tvJudul.text = berita.title
            Picasso.get().load(berita.url_image).into(binding.imageBerita)
            val p = URLImageParser(tvBerita, this@InformasiActivity)
            val htmlSpan =
                Html.fromHtml(berita.description ?: "", Html.FROM_HTML_MODE_COMPACT, p, null)
            tvBerita.text = htmlSpan
        }
    }

    private fun getDetailInfo() {
        viewModel.getInfo().observe(this, {
            when (it.state) {
                State.SUCCESS -> {
                    berita = it.data ?: Berita()

                    val image = berita.image
                    if (image == null || image == "kosong") binding.imageBerita.toGone()

                    setData()
                    binding.lyPd.toGone()
                }
                State.ERROR -> {

                }
                State.LOADING -> {

                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}