package com.example.android.flinkgoangelhack.base

abstract class BasePresenter<V> {
    protected var view : V? = null

    fun attachView(v: V) {
        view = v
    }

    fun detachView() {
        view = null
    }
}