package com.example.mdbforgloo.data

data class TopRated(
    val page: Int,
    val results: List<TopRatedResult>,
    val total_pages: Int,
    val total_results: Int
)