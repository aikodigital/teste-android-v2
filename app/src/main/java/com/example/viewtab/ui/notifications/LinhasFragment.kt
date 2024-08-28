package com.example.viewtab.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewtab.databinding.FragmentMapsSeaschBinding
import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.modelNerwork.Status
import com.example.viewtab.ui.notifications.adapter.AdapterSearchLinha
import com.example.viewtab.util.ScreenManager

class LinhasFragment : Fragment() {

    private var mBinding: FragmentMapsSeaschBinding? = null

    private var mViewModel: LinhasViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[LinhasViewModel::class.java]

        mBinding = FragmentMapsSeaschBinding.inflate(inflater, container, false)
        setListener()

        return mBinding!!.root
    }


    private fun setListener() {
        mBinding?.resultSearch?.isVisible = true
        mBinding?.resultList?.isVisible = true

        mViewModel?.mItems?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) {
                val resource: Resource<List<Linha?>?>? = mViewModel?.mItems?.get()

                if (resource?.status === Status.SUCCESS) {
                    setParadasSearch(resource.data)
                } else if (resource?.status === Status.ERROR) {

                }
            }
        })

        mBinding?.searchInputLayout?.setEndIconOnClickListener {
            val searchQuery = mBinding?.searchEditText?.text.toString()
            if (!searchQuery.isNullOrEmpty()) {
                mViewModel?.loadRepositoriesList(searchQuery)
            }
        }
    }

    private fun setParadasSearch(data: List<Linha?>?) {
        mBinding?.resultSearch?.isVisible = true
        mBinding?.resultList?.isVisible = true
        if (!data.isNullOrEmpty()){
            val adapter = AdapterSearchLinha()

            mBinding?.resultList?.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(context)
            }

            adapter.submitList(ArrayList(data)){
                //ScreenManager.toGoParadaView(context,it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}