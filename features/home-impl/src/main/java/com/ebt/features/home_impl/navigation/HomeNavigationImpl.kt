package com.ebt.features.home_impl.navigation

import androidx.fragment.app.FragmentActivity
import com.ebt.features.home_api.HomeNavigation
import com.ebt.features.home_impl.HomeFragment
import javax.inject.Inject

private const val HOME_FRAGMENT_TAG = "home_fragment_tag"

class HomeNavigationImpl @Inject constructor(
    private val activity: FragmentActivity
) : HomeNavigation{
    override fun showHome(containerId: Int) {
        activity.supportFragmentManager.apply {
            val homeFragment = findFragmentByTag(HOME_FRAGMENT_TAG)

            if (homeFragment == null) {
                beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(containerId, HomeFragment(), HOME_FRAGMENT_TAG)
                    .commitNow()
            }
        }
    }
}