package com.github.grubber.tiangou_kotlin.data.exception

/**
 * Created by grubber on 2017/1/6.
 */
data class ApiError(
        var _code: Long = -1L,
        var _msg: String
)