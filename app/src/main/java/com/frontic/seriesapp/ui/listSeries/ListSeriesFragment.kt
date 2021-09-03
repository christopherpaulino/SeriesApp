package com.frontic.seriesapp.ui.listSeries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.frontic.seriesapp.R
import com.frontic.seriesapp.adapters.ListSeriesAdapter
import com.frontic.seriesapp.models.Series
import com.frontic.seriesapp.ui.base.BaseFragment
import com.frontic.seriesapp.ui.detailsSeries.DetailsSeriesActivity
import com.frontic.seriesapp.utils.RecyclerViewPagination

class ListSeriesFragment : BaseFragment(), ListSeriesContract.View,
    ListSeriesAdapter.AdapterListener {

    private lateinit var presenter: ListSeriesContract.Presenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresher: SwipeRefreshLayout
    private lateinit var adapter: ListSeriesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_list_series, container, false)

        initializeVariables(root)
        setHasOptionsMenu(true)

        return root
    }


    override fun onDetach() {
        super.onDetach()
        presenter.destroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun initializeVariables(view: View) {
        super.initializeVariables(view)
        presenter = ListSeriesPresenter(this, requireContext())

        adapter = context?.let { context ->
            ListSeriesAdapter(context, this)
        }!!

        swipeRefresher = view.findViewById(R.id.swipe_refresh)
        progressBar = view.findViewById(R.id.loading_pbar)
        recyclerView = view.findViewById(R.id.list_series_rv)
        recyclerView.adapter = adapter

//        noNetworkView = view.findViewById(R.id.no_internet_screen)

        swipeRefresher.setOnRefreshListener {
            presenter.getSeries(initialPage)
        }

        presenter.getSeries(initialPage)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.list_series_menu, menu)

        val searchItem = menu.findItem(R.id.search_item)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                query?.let { presenter.searchSeries(it) }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        searchView.setOnCloseListener {
            presenter.getSeries(initialPage)
            true
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Fragment.hideKeyboard() {
        view?.let {
            activity?.hideKeyboard(it)
        }
    }

    override fun showLoading(show: Boolean) {
        swipeRefresher.isRefreshing = show
    }

    override fun showSearchResult(list: List<Series>) {
        adapter.clear()
        adapter.addAllItems(list)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {})
    }

    override fun showList(list: List<Series>) {

        if (currentPage == 0) {
            adapter.clear()
        }

        adapter.addAllItems(list)

        recyclerView.addOnScrollListener(object : RecyclerViewPagination(recyclerView) {

            override fun loadMore() {
                presenter.getSeries(nextPage)
            }

            override fun isLoading(): Boolean {
                return swipeRefresher.isRefreshing
            }

            override fun isLastPage(): Boolean {
                return list.isEmpty()
            }
        })
    }

    override fun goToSeries(series: Series) {
        val bundle = Bundle()
        bundle.putSerializable("series", series)

        val intent = Intent(requireContext(), DetailsSeriesActivity::class.java)
        intent.putExtra("series",series)

        startActivity(intent)
    }

    override fun showNoNetworkConnected(t: Boolean) {
        recyclerView.visibility = if (t) View.GONE else View.VISIBLE
//        noNetworkView.visibility = if (t) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
//        showAlertMessage(message)
    }

    companion object {
        private val initialPage
            get():Int {
                currentPage = 0
                return 0
            }

        private var currentPage = 0

        private val nextPage
            get() : Int {
                currentPage += 1
                return currentPage
            }
    }
}