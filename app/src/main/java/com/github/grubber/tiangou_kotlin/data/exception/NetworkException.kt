package com.github.grubber.tiangou_kotlin.data.exception

/**
 * Created by grubber on 2017/1/6.
 */
class NetworkException(detailMessage: String, throwable: Throwable?) : ApiException(detailMessage, throwable)