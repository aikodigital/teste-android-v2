package com.example.viewtab.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.repositories.ParadasRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ParadasViewModel : ViewModel() {

    private val mParadasRepository: ParadasRepository = ParadasRepository.getInstance()

    val mItemsMaps: ObservableField<Resource<List<Parada?>?>> = ObservableField()
    val mItemsSearch: ObservableField<Resource<List<Parada?>?>> = ObservableField()

    private val mCompositeDisposable = CompositeDisposable()

    fun loadRepositoriesList(page: String) {

        val disposable: Disposable? = mParadasRepository.getBuscar(page)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { listResource ->
                mItemsMaps.set(listResource)
            }

        disposable?.apply {addDisposable(this)}
    }

    fun loadRepositoriesListSearch(page: String) {

        val disposable: Disposable? = mParadasRepository.getBuscar(page)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { listResource ->
                mItemsSearch.set(listResource)
            }

        disposable?.apply {addDisposable(this)}
    }

    private fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onCleared() {
        mCompositeDisposable.clear()
    }
}