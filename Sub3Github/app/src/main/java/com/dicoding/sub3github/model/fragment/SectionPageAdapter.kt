package com.dicoding.sub3github.model.fragment

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.sub3github.R

class SectionPageAdapter(private val mContext: Context, fm: FragmentManager, mData: Bundle) :
    FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    private var mfragmentBundle: Bundle

    init {
        mfragmentBundle = mData
    }

    @StringRes
    private val TITLES = intArrayOf(R.string.tabs_1, R.string.tabs_2)


    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.mfragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TITLES[position])
    }
}