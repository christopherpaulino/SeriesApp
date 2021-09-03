package com.frontic.seriesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frontic.seriesapp.R
import com.frontic.seriesapp.api.GlideApi
import com.frontic.seriesapp.models.Episode
import com.frontic.seriesapp.models.SeasonAdapterItem

/**
 * Adapter to display episodes list.
 *
 * @param listener     [SeasonListAdapter.AdapterListener] instance for interacting
 * with the implementer.
 * @param season       [SeasonAdapterItem] elements to be loaded.
 *
 * @author Christopher Paulino.
 */

class EpisodesListAdapter(
    private val listener: SeasonListAdapter.AdapterListener,
    private val season: SeasonAdapterItem
) :
    RecyclerView.Adapter<EpisodesListAdapter.ViewHolder>() {

    private val list: List<Episode> = season.episodes ?: ArrayList()
    private val context = listener.getViewContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.episode_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = list[position]

        holder.apply {
            title.text = episode.name

            GlideApi(context).loadImageFromUrl(episode.image?.medium ?: "", poster)

            itemView.setOnClickListener {
                listener.goToEpisode(episode, season.name)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    /**
     * ViewHolder that represent each episode item.
     *
     * @param itemView Layout view instance.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.episode_title_tv)
        val poster: ImageView = itemView.findViewById(R.id.poster_iv)
    }
}