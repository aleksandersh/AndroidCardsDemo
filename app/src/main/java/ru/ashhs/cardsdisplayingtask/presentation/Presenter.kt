package ru.ashhs.cardsdisplayingtask.presentation

import android.util.Log
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Basic realization of Presenter.
 */
open class Presenter<T> {
    protected var view: T? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun bindView(view: T) {
        this.view = view
    }

    fun unbindView() {
        view = null
        compositeDisposable.clear()
    }

    protected fun <U> loadFromSingleSource(source: Single<U>, onCompleteMethod: (U) -> Unit, onErrorMethod: (Throwable) -> Unit) {
        compositeDisposable.add(source
                .subscribe(onCompleteMethod,
                        { error ->
                            run {
                                onError(error)
                                onErrorMethod(error)
                            }
                        }))
    }

    open protected fun onError(error: Throwable) {
        Log.d("Presenter", error.message ?: error.toString())
    }
}