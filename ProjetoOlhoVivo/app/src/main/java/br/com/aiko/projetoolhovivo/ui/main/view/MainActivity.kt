package br.com.aiko.projetoolhovivo.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import br.com.aiko.projetoolhovivo.R
import br.com.aiko.projetoolhovivo.databinding.ActivityMainBinding
import br.com.aiko.projetoolhovivo.ui.line.LineViewModel
import br.com.aiko.projetoolhovivo.ui.main.MainViewModel
import br.com.aiko.projetoolhovivo.ui.main.adapter.MainViewPagerAdapter
import br.com.aiko.projetoolhovivo.ui.position.PositionMapViewModel
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val lineViewModel: LineViewModel by viewModels { viewModelFactory }

    private val positionMapViewModel: PositionMapViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.token = getString(R.string.app_token_sp_trans)
        lineViewModel.token = getString(R.string.app_token_sp_trans)
        positionMapViewModel.token = getString(R.string.app_token_sp_trans)

        viewModel.error.observe(this) { error ->
            run {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.auth()
        setupView()
    }

    private fun setupView() {
        val tabLayout: TabLayout = binding.tabMain
        val viewPager: ViewPager2 = binding.viewPager

        val adapter = MainViewPagerAdapter(this)
        viewPager.adapter = adapter

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.main_tab_map)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.main_tab_list_linha)))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
        viewPager.isUserInputEnabled = false
    }
}