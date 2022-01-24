package com.allaykhalil.demo.movifetcher.model.base

data class ResponseError(
    var statusCode: Int? = null,
    var errorMessage: String? = null
)