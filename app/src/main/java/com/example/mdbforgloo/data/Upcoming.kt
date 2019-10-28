package com.example.mdbforgloo.data

data class Upcoming(
    val dates: DatesX,
    val page: Int,
    val results: List<UpcomingResult>,
    val total_pages: Int,
    val total_results: Int
)