package com.github.grubber.tiangou_kotlin.data.exception

/**
 * Created by grubber on 2017/1/6.
 */
class ServerException(detailMessage: String, throwable: Throwable?, val apiError: ApiError) : ApiException(detailMessage, throwable)