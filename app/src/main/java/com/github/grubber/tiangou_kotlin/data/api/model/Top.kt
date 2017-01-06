package com.github.grubber.tiangou_kotlin.data.api.model

/**
 * Created by grubber on 2017/1/6.
 */
data class Top(
        val title: String,
        val topclass: Int,
        val img: String,
        val description: String,
        val keywords: String,
        val message: String,
        val count: Int,
        val fcount: Int,
        val rcount: Int,
        val fromname: String,
        val fromurl: String,
        val time: Long
)

data class Result(
        val total: Long,
        val tngou: List<Top>
)