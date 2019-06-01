package com.example.myapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.myapplication.Fragment.CoffeeFragment
import com.example.myapplication.Fragment.HistoryTransactionFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        CoffeeFragment(),
        HistoryTransactionFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Coffee Data"
            1 -> "Report"
            else -> ""
        }
    }
}