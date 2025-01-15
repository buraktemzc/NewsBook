package com.ebt.features.detail_impl.navigation

import androidx.fragment.app.FragmentActivity
import com.ebt.features.detail_api.DetailNavigation
import com.ebt.features.detail_impl.DetailFragment
import javax.inject.Inject

private const val DETAIL_FRAGMENT_TAG = "detail_fragment_tag"
private const val DETAIL_FRAGMENT_BACKSTACK_NAME = "detail_fragment_backstack_name"


class DetailNavigationImpl @Inject constructor(
    private val activity: FragmentActivity
) : DetailNavigation {

    override fun showDetail(containerId: Int, rowId: Long) {
        activity.supportFragmentManager.apply {
            val existingFragment = findFragmentByTag(DETAIL_FRAGMENT_TAG)

            if (existingFragment == null) {
                beginTransaction()
                    .setReorderingAllowed(true)
                    .add(containerId, DetailFragment.newInstance(rowId))
                    .addToBackStack(DETAIL_FRAGMENT_BACKSTACK_NAME)
                    .commit()
                executePendingTransactions()
            }
        }
    }
}