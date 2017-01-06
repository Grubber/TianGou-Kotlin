package com.github.grubber.tiangou_kotlin.data.exception

import retrofit.RetrofitError

/**
 * Created by grubber on 2017/1/6.
 */
open class ApiException : RuntimeException {
    constructor(throwable: Throwable?) : super(throwable)

    constructor(detailMessage: String, throwable: Throwable?) : super(detailMessage, throwable)

    companion object {
        fun create(retrofitError: RetrofitError): ApiException {
            val throwable = retrofitError.cause

            when (retrofitError.kind.ordinal) {
                0 -> return NetworkException("network error", throwable)

                1 -> return GsonConversionException("gson convert error", throwable)

                2 -> {
                    try {
                        val apiError = retrofitError.getBodyAs(ApiError::class.java)

                        if (apiError is ApiError) {
                            return ServerException("server response error:" + " " + apiError.toString(), throwable, apiError)
                        } else {
                            return UnexpectedException("unexpected error", throwable)
                        }
                    } catch(e: Exception) {
                        return UnexpectedException("unexpected error", throwable)
                    }
                }

                else -> return UnexpectedException("unexpected error", throwable)
            }
        }
    }
}