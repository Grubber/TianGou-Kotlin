package com.github.grubber.tiangou_kotlin.ui.base

import android.os.Bundle
import com.github.grubber.tiangou_kotlin.TGApplication
import com.github.grubber.tiangou_kotlin.core.di.ApplicationComponent
import com.github.grubber.tiangou_kotlin.core.di.HasComponent
import com.github.grubber.tiangou_kotlin.core.di.Injectable
import com.trello.rxlifecycle.ActivityEvent
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by grubber on 2017/1/6.
 */
abstract class BaseActivity : RxAppCompatActivity(), HasComponent<ApplicationComponent>, Injectable {
    protected fun <T> bind(observable: Observable<T>) =
            observable.compose(this.bindToLifecycle<T>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    protected fun <T> bind(observable: Observable<T>, activityEvent: ActivityEvent) =
            observable.compose(this.bindUntilEvent<T>(activityEvent))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMembers()
    }

    override fun getComponent(): ApplicationComponent {
        return TGApplication.from(this).getComponent()
    }
}