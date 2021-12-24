package com.example.smashnews.ui.berita

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import com.example.smashnews.core.data.source.remote.model.Berita
import com.example.smashnews.core.data.source.remote.model.Response
import com.example.smashnews.core.data.source.remote.network.State
import com.example.smashnews.core.data.source.remote.request.ResponseRequest
import com.example.smashnews.databinding.ActivityDetailBeritaBinding
import com.example.smashnews.ui.base.MyActivity
import com.example.smashnews.ui.berita.adapter.ResponseAdapter
import com.example.smashnews.ui.main.MainViewModel
import com.example.smashnews.ui.main.adapter.BeritaAdapter
import com.example.smashnews.util.Constants
import com.example.smashnews.util.Prefs
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.smashnews.util.base64Handler.URLImageParser


class DetailBeritaActivity : MyActivity() {

    private val viewModel: MainViewModel by viewModel()

    private var _binding: ActivityDetailBeritaBinding? = null
    private val binding get() = _binding!!
    private val adapter = BeritaAdapter()
    private val adapterResponse = ResponseAdapter()
    private var berita = Berita()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Detail Berita")

        setupAdapter()
        getIntentExtra()

    }

    @SuppressLint("SetTextI18n")
    private fun getIntentExtra() {
        berita = getStringExtra().toModel(Berita::class.java) ?: Berita()

        setData()
        loadBeritaByCategory(berita.slugCategory)
        getDetailBerita(berita.slug)
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        binding.apply {
            tvJudul.text = berita.name
            tvAuthor.text = berita.author
            tvTgl.text =
                berita.publish_at?.convertTanggal(berita.publish_at!!, "dd MMMM yyy kk:mm") + " WIB"
            Picasso.get().load(Constants.IMAGE_URL + berita.image).into(binding.imageBerita)
//            tvBerita.text = Html.fromHtml(berita.description, Html.FROM_HTML_MODE_COMPACT)
            val p = URLImageParser(tvBerita, this@DetailBeritaActivity)
            val htmlSpan =
                Html.fromHtml(berita.description ?: "", Html.FROM_HTML_MODE_COMPACT, p, null)
            tvBerita.text = htmlSpan
        }
    }

    private fun setupAdapter() {
        binding.rvBerita.adapter = adapter
        binding.rvResponse.adapter = adapterResponse
        adapterResponse.onClick = {
            postResponse(it)
        }
    }

    private fun loadBeritaByCategory(slugCategory: String?) {
        viewModel.getBeritaByCategory(slugCategory).observe(this, { it ->
            when (it.state) {
                State.SUCCESS -> {
                    adapter.clear()
                    val list = it.data?.filter { it.id != berita.id }
                    adapter.addItem(list ?: emptyList())
                    binding.tvKosong.visible(list.isNullOrEmpty())
                }
                State.ERROR -> {

                }
                State.LOADING -> {

                }
            }
        })
    }

    private var sendResponse = false
    private fun postResponse(response: Response) {

        if (sendResponse) {
            toastWarning("Respon hanya bisa dikirim sekali")
            return
        }

        if (Prefs.isThereResponse(berita.slug ?: "")) {
            toastWarning("Respon hanya bisa dikirim sekali")
            return
        }

        val body = ResponseRequest(
            article = berita.slug,
            response = response.slug
        )

        viewModel.postResponse(body).observe(this, {
            when (it.state) {
                State.SUCCESS -> {
                    sendResponse = true
                    progress.dismiss()
                    Prefs.saveResponse(berita.slug)
                    getDetailBerita(berita.slug)
                    toastSuccess("Response berhasil dikirim")
                }
                State.ERROR -> {
                    progress.dismiss()
                    toastError(it.message ?: "Terjadi kesalahan")
                }
                State.LOADING -> {
                    progress.show()
                }
            }
        })
    }

    private fun getDetailBerita(slug: String?) {
        viewModel.getDetailBerita(slug).observe(this, {
            when (it.state) {
                State.SUCCESS -> {
                    adapter.clear()
                    adapterResponse.clear()
                    berita = it.data?.article ?: Berita()
                    adapterResponse.addItem(it.data?.responses ?: emptyList())
                    binding.tvTag.visible(berita.tags.isNotEmpty())
                    var tags = ""
                    berita.tags.forEach { tag ->
                        tags += "#${tag.name} "
                    }
                    binding.tvTag.text = tags
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