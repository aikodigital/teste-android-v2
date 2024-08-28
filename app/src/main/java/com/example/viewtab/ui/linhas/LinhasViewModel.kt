package com.example.viewtab.ui.linhas

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.repositories.LinhasRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LinhasViewModel : ViewModel() {

    private val mParadasRepository: LinhasRepository = LinhasRepository.getInstance()

    val mItems: ObservableField<Resource<List<Linha?>?>> = ObservableField()

    private val mCompositeDisposable = CompositeDisposable()

    fun loadRepositoriesList(page: String) {

        val disposable: Disposable? = mParadasRepository.getBuscar(page)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { listResource ->
                mItems.set(listResource)
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