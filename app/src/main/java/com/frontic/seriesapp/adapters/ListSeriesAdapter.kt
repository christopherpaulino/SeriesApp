package com.frontic.seriesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frontic.seriesapp.R
import com.frontic.seriesapp.api.GlideApi
import com.frontic.seriesapp.models.Series

/**
 * Adapter to display series list, both local and remote ones.
 *
 * @param context     Application context.
 * @param listener   [ListSeriesAdapter.AdapterListener] instance for interacting
 * with the implementer.
 *
 * @author Christopher Paulino.
 */
class ListSeriesAdapter(
    private val context: Context,
    private val listener: AdapterListener
) :
    RecyclerView.Adapter<ListSeriesAdapter.ViewHolder>() {

    private val listItems = ArrayList<Series>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.series_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]

        holder.apply {
            title.text = item.name
            genresList.text = item.getGenresListAsString()
            network.text = item.getNetworkName()

            item.image?.medium?.let {
                GlideApi(context).loadImageFromUrl(item.image.medium, poster)
            }

            itemView.setOnClickListener {
                listener.goToSeries(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    /**
     * Add list of [Series] to the list loaded in the RecyclerView.
     *
     * @param seriesList List of [Series] intances.
     */
     fun addAllItems(seriesList : List<Series>) {
        listItems.addAll(seriesList)
         notifyDataSetChanged()
    }

    /**
     * Remove one element of series list.
     *
     * @param series Individual item of [Series] instance.
     */
    private fun remove(series: Series) {
        val position = listItems.indexOf(series)

        if (position > -1) {
            listItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * Call for removing all the item in the Recycler View
     */
    fun clear() {
        while (itemCount > 0) {
            remove(listItems[0])
        }
        notifyDataSetChanged()
    }

    /**
     * Interface for interacting with the adapter host.
     */
    interface AdapterListener {
        fun goToSeries(series: Series)
    }

    /**
     * View Holder that represent a series list item.
     *
     * @param view View resource.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_tv)
        val network: TextView = view.findViewById(R.id.network_tv)
        val poster: ImageView = view.findViewById(R.id.poster_iv)
        val genresList: TextView = view.findViewById(R.id.genres_tv)
    }


}