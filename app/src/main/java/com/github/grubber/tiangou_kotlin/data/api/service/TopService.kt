package com.github.grubber.tiangou_kotlin.data.api.service

import com.github.grubber.tiangou_kotlin.data.api.model.Result
import retrofit.http.GET
import rx.Observable

/**
 * Created by grubber on 2017/1/6.
 */
interface TopService {
    @GET("/top/list")
    fun list(): Observable<Result>
}