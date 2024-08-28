package com.example.viewtab.ui.posicao

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.model.Posicao
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.repositories.PosicaoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PosicaoViewModel : ViewModel() {

    private val mPosicaoRepository: PosicaoRepository = PosicaoRepository.getInstance()

    val mItemsMaps: ObservableField<Resource<Posicao?>> = ObservableField()

    private val mCompositeDisposable = CompositeDisposable()

    fun loadRepositoriesList() {

        val disposable: Disposable? = mPosicaoRepository.getBuscar()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { listResource ->
                mItemsMaps.set(listResource)
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