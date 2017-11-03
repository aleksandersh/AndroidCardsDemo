package ru.ashhs.cardsdisplayingtask.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

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

    /**
     * Call this method on chain after subscription.
     */
    protected fun Disposable.collect() {
        compositeDisposable.add(this)
    }
}