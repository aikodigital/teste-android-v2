package com.example.viewtab.ui.paradaView

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.model.Previsao
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.repositories.PrevisaoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ParadaViewViewModel : ViewModel() {

    private val mPrevisaoRepository: PrevisaoRepository = PrevisaoRepository.getInstance()

    val mItems: ObservableField<Resource<Previsao?>> = ObservableField()

    private val mCompositeDisposable = CompositeDisposable()

    fun loadRepositoriesList(code: Long) {

        val disposable: Disposable? = mPrevisaoRepository.getParadaPrecisao(code)
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