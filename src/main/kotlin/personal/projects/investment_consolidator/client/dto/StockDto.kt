package personal.projects.investment_consolidator.client.dto

import java.time.ZonedDateTime

class StockDto(
    val currency: String,
    val shortName: String,
    val longName: String,
    val regularMarketTime: ZonedDateTime,
    val regularMarketPrice: Double,
    val symbol: String,
    val logourl: String
)