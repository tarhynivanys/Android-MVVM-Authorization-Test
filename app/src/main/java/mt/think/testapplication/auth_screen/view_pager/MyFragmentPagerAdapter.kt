package mt.think.testapplication.auth_screen.view_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT

class MyFragmentPagerAdapter<T : Fragment>(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments: ArrayList<T> = arrayListOf()

    fun addFragment(fragment: T) {
        fragments.add(fragment)
    }

    override fun getItem(index: Int) = fragments[index]

    override fun getCount() = fragments.size
}
