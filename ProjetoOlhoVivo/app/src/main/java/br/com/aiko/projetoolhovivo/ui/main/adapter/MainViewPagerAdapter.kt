package br.com.aiko.projetoolhovivo.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.aiko.projetoolhovivo.ui.line.view.ListLineFragment
import br.com.aiko.projetoolhovivo.ui.position.view.MapPositionFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MapPositionFragment()
            1 -> ListLineFragment()
            else -> MapPositionFragment()
        }
    }
}