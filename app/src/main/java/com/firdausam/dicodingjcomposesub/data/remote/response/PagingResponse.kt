package com.firdausam.dicodingjcomposesub.data.remote.response

data class PagingResponse<T>(
    val pagination: Any,
    val data: List<T>
)