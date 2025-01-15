package com.ebt.features.detail_impl

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.ebt.core.ui.extensions.observeInLifecycle
import com.ebt.core.ui.view.ErrorDialog
import com.ebt.core.ui.view.LoadingDialog
import com.ebt.features.detail_impl.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.ebt.core.ui.R as coreR

private const val ARG_ROW_ID = "arg_row_id"

@AndroidEntryPoint
class DetailFragment : Fragment() {

    @Inject
    lateinit var errorDialog: ErrorDialog

    @Inject
    internal lateinit var loadingDialog: LoadingDialog

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionInflater
            .from(requireContext())
            .inflateTransition(coreR.transition.fade_in_out)
        exitTransition = TransitionInflater
            .from(requireContext())
            .inflateTransition(coreR.transition.fade_out)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rowId = arguments?.getLong(ARG_ROW_ID)
            ?: throw IllegalArgumentException("Use newInstance(rowId: Long) method to create this fragment")
        initUI()
        initObservers()
        viewModel.getDetail(rowId)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initUI() {
        binding.updateButton.setOnClickListener {
            viewModel.update(
                title = binding.titleEditText.text.toString(),
                description = binding.descriptionEditText.text.toString()
            )
        }
    }

    private fun initObservers() {
        viewModel.detailFlow.observeInLifecycle(viewLifecycleOwner) {
            when (it) {
                is DetailEvent.Show -> {
                    loadingDialog.dismiss()

                    val uiModel = it.uiModel
                    with(binding) {
                        userImageView.load(uiModel.imageURL) {
                            transformations(
                                CircleCropTransformation()
                            )
                        }
                        titleEditText.setText(uiModel.title)
                        descriptionEditText.setText(uiModel.description)
                    }
                }

                is DetailEvent.Updated -> {
                    loadingDialog.dismiss()
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }

                is DetailEvent.NotUpdated -> {
                    loadingDialog.dismiss()
                    showMessage(getString(coreR.string.error_update))
                }

                is DetailEvent.Loading -> loadingDialog.show()
            }
        }
    }

    private fun showMessage(message: String) {
        errorDialog.setErrorMessage(message)
        errorDialog.show()
    }

    companion object {
        fun newInstance(rowId: Long) = DetailFragment().apply {
            arguments = bundleOf(
                ARG_ROW_ID to rowId
            )
        }
    }
}