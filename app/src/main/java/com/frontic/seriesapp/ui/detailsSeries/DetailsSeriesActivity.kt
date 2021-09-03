package com.frontic.seriesapp.ui.detailsSeries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.frontic.seriesapp.R
import com.frontic.seriesapp.adapters.SeasonListAdapter
import com.frontic.seriesapp.api.GlideApi
import com.frontic.seriesapp.databinding.ActivityDetailsSeriesBinding
import com.frontic.seriesapp.models.Episode
import com.frontic.seriesapp.models.SeasonAdapterItem
import com.frontic.seriesapp.models.Series
import com.frontic.seriesapp.ui.detailsEpisode.DetailsEpisodeActivity

class DetailsSeriesActivity : AppCompatActivity(), DetailsSeriesContract.View,
    SeasonListAdapter.AdapterListener {

    private lateinit var series: Series
    private lateinit var presenter: DetailsSeriesContract.Presenter

    private lateinit var binding: ActivityDetailsSeriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_series)
        setContentView(binding.root)

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        initializeVariables()

        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.destroy()
    }

    override fun onPause() {
        super.onPause()

        presenter.destroy()
    }

    private fun initializeVariables() {

        intent.extras?.let {
            series = it.getSerializable("series") as Series
        }
        presenter = DetailsSeriesPresenter(this)

    }

    private fun loadData() {
        presenter.getSeasonsBySeries(series.id)

        binding.apply {
            titleTv.text = series.name
            networkTv.text = series.getNetworkName()
            genresTv.text = series.getGenresListAsString()
            timeTv.text = series.schedule.time ?: ""
            dateTv.text = series.getScheduleDaysAsString()
            summaryTv.text = Html.fromHtml(series.summary, Html.FROM_HTML_MODE_LEGACY)

            GlideApi(this@DetailsSeriesActivity).loadImageFromUrl(
                series.image?.original ?: "",
                posterIv
            )
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showSeasons(list: List<SeasonAdapterItem>) {
        val adapter = SeasonListAdapter(this, list)
        binding.seasonsRv.adapter = adapter
    }

    override fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun goToEpisode(episode: Episode, seasonName: String) {
        val intent = Intent(this, DetailsEpisodeActivity::class.java)
        intent.putExtra("episode", episode)
        intent.putExtra("season",seasonName)

        startActivity(intent)
    }

    override fun getViewContext(): Context {
        return this
    }

}