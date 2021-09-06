package com.frontic.seriesapp.ui.listSeries

import com.frontic.seriesapp.models.Series
import com.frontic.seriesapp.ui.base.BaseContract

/**
 * Contract that contains the interfaces to be implemented in List Series functionality.
 *
 * @author Christopher Paulino
 */
class ListSeriesContract {

    /**
     * Interface contract for interacting with the View in List Series functionality.
     */
    interface Presenter {

        /**
         * Request list of series by page.
         */
        fun getSeries(page: Int)

        /**
         * Perform request by search criteria.
         */
        fun searchSeries(criteria: String)
    }

    /**
     * Interface contract for interacting with the View in List Series functionality.
     */
    interface View : BaseContract.BaseView {

        /**
         * Load a list of series after performing a request.
         */
        fun showList(list: List<Series>)

        /**
         * Take an action if there is not network connected.
         */
        fun showNoNetworkConnected(t: Boolean)

        /**
         * Load a list of search result after performing a request.
         */
        fun showSearchResult (list: List<Series>)

        /**
         * Go to Series Details
         */
        fun goToSeries(series: Series)
    }
}