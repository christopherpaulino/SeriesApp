package com.frontic.seriesapp.ui.detailsEpisode

import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.frontic.seriesapp.R
import com.frontic.seriesapp.api.GlideApi
import com.frontic.seriesapp.databinding.ActivityDetailsEpisodeBinding
import com.frontic.seriesapp.models.Episode
import com.frontic.seriesapp.ui.base.BaseActivity

class DetailsEpisodeActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsEpisodeBinding
    private lateinit var episode: Episode
    private lateinit var seasonName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_episode)
        setContentView(binding.root)

        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        initializeVariable()
        loadData()

//        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
//        val viewPager: ViewPager = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tabs)
//        tabs.setupWithViewPager(viewPager)
//        val fab: FloatingActionButton = findViewById(R.id.fab)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initializeVariable() {
        intent.extras?.run {
            episode = getSerializable("episode") as Episode
            seasonName = getString("season").toString()
        }
    }

    override fun loadData() {
        binding.apply {
            titleTv.text = episode.name
            seasonTv.text = seasonName
            episodeNumberTv.text = "${getString(R.string.episode)}: ${episode.number}"
            timeTv.text = episode.airtime ?: ""
            dateTv.text = episode.airdate ?: ""
            summaryTv.text = Html.fromHtml(episode.summary, Html.FROM_HTML_MODE_LEGACY)

            GlideApi(this@DetailsEpisodeActivity).loadImageFromUrl(
                episode.image?.original ?: "",
                posterIv
            )
        }
    }
}