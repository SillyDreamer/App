package com.example.app

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager, context : Context) : FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница")

    override fun getItem(position: Int): Fragment {
        return FragmentPage().newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}