package personal.projects.investment_consolidator.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import personal.projects.investment_consolidator.client.dto.BrapiResponseDto

@FeignClient(
    name = "Brapi",
    url = "https://brapi.dev",
    configuration = [ClientConfig::class]
)
interface BrapiClient {

    @GetMapping("/api/quote/{ticker}/")
    fun getStockPrice(
        @PathVariable("ticker") stockId: String,
        @RequestParam("token") token: String
    ): BrapiResponseDto
}