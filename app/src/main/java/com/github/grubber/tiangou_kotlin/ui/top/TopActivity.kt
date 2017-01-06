package com.github.grubber.tiangou_kotlin.ui.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import butterknife.bindView
import com.github.grubber.tiangou_kotlin.R
import com.github.grubber.tiangou_kotlin.data.api.service.TopService
import com.github.grubber.tiangou_kotlin.ui.base.BaseActivity
import com.github.grubber.tiangou_kotlin.util.ToastHelper
import com.squareup.picasso.Picasso
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/6.
 */
class TopActivity : BaseActivity() {
    @Inject
    lateinit var _topService: TopService
    @Inject
    lateinit var _picasso: Picasso
    @Inject
    lateinit var _toastHelper: ToastHelper

    private val _listView by bindView<ListView>(R.id.listView)

    private var _topAdapter: TopAdapter by Delegates.notNull<TopAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        _topAdapter = TopAdapter(this, _picasso)
        _listView.adapter = _topAdapter

        bind(_topService.list())
                .subscribe({
                    _topAdapter.setResult(it.tngou)
                }, {
                    Timber.e("### onError. error is $it")
                })
    }

    override fun injectMembers() {
        getComponent().inject(this)
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, TopActivity::class.java))
    }
}