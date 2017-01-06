package com.github.grubber.tiangou_kotlin.ui

import android.os.Bundle
import com.github.grubber.tiangou_kotlin.ui.base.BaseActivity
import com.github.grubber.tiangou_kotlin.ui.top.TopActivity
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by grubber on 2017/1/6.
 */
class HomeActivity : BaseActivity() {
    @Inject
    lateinit var _bus: Bus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _bus.register(this)
        _bus.post(OnStartTopActivityEvent())
    }

    override fun injectMembers() {
        getComponent().inject(this)
    }

    @Subscribe
    fun onStartTopActivity(event: OnStartTopActivityEvent) {
        TopActivity.start(this)
        finish()
    }

    override fun onDestroy() {
        _bus.unregister(this)
        super.onDestroy()
    }
}

class OnStartTopActivityEvent