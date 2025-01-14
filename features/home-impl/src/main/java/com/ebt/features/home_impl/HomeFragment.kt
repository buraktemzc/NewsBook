package com.ebt.features.home_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebt.core.model.NewsError
import com.ebt.core.ui.extensions.observeInLifecycle
import com.ebt.core.ui.view.ErrorDialog
import com.ebt.core.ui.view.LoadingDialog
import com.ebt.features.home_impl.adapter.HomeAdapter
import com.ebt.features.home_impl.databinding.FragmentHomeBinding
import com.ebt.features.home_impl.listener.HomeListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    internal lateinit var homeAdapterFactory: HomeAdapter.HomeAdapterFactory

    @Inject
    internal lateinit var homeListener: HomeListener

    @Inject
    internal lateinit var errorDialog: ErrorDialog

    @Inject
    internal lateinit var loadingDialog: LoadingDialog

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter by lazy {
        homeAdapterFactory.create(::onNewsSelected)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
        viewModel.checkNewsAreReady()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initUI() {
        with(binding) {
            with(recyclerView) {
                val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
                ContextCompat.getDrawable(this.context, R.drawable.list_divider)
                    ?.let { divider.setDrawable(it) }
                layoutManager =
                    LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(divider)
                adapter = homeAdapter
            }
        }
        errorDialog.setOnDismissListener {
            viewModel.needToRunInitialOperations()
        }
    }

    private fun initObservers() {
        viewModel.homeFlow.observeInLifecycle(viewLifecycleOwner) {
            when (it) {
                is HomeEvent.Loading -> loadingDialog.show()
                is HomeEvent.NewsRetrieved -> {
                    loadingDialog.dismiss()
                    homeAdapter.submitList(it.list)
                }

                is HomeEvent.Failure -> {
                    loadingDialog.dismiss()
                    showError(it.error)
                }
            }
        }
    }

    private fun showError(error: NewsError) {
        when (error) {
            is NewsError.ApiError -> showMessage(error.message)
            is NewsError.UnKnownError -> showMessage(getString(com.ebt.core.ui.R.string.error_unknown))
            is NewsError.ConnectionError -> showMessage(getString(com.ebt.core.ui.R.string.error_connection))
            is NewsError.TimeOutException -> showMessage(getString(com.ebt.core.ui.R.string.error_timeout))
        }
    }

    private fun showMessage(message: String) {
        errorDialog.setErrorMessage(message)
        errorDialog.show()
    }

    private fun onNewsSelected(rowId: Long) {
        homeListener.onNewsSelected(rowId)
    }
}