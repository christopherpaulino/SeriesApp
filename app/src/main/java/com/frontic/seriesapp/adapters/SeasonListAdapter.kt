package com.frontic.seriesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frontic.seriesapp.R
import com.frontic.seriesapp.models.Episode
import com.frontic.seriesapp.models.SeasonAdapterItem

/**
 * Adapter to display seasons list.
 *
 * @param listener     [SeasonListAdapter.AdapterListener] instance for interacting
 * with the implementer.
 * @param listItems    [SeasonAdapterItem] elements to be loaded.
 *
 * @author Christopher Paulino.
 */
class SeasonListAdapter(
    private val listener: AdapterListener,
    private val listItems: List<SeasonAdapterItem>
) :
    RecyclerView.Adapter<SeasonListAdapter.ViewHolder>() {

    private val context = listener.getViewContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.season_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val season = listItems[position]

        holder.apply {
            title.text = season.name
            episodes.adapter = EpisodesListAdapter(listener, season)
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    /**
     * Interface for interacting with the adapter host.
     */
    interface AdapterListener {
        fun goToEpisode(episode: Episode, seasonName: String)
        fun getViewContext(): Context
    }

    /**
     * View Holder that represent a season list item.
     *
     * @param itemView View resource.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.season_title_tv)
        val episodes: RecyclerView = itemView.findViewById(R.id.episodes_rv)
    }
}