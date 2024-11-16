package personal.projects.investment_consolidator.client

import feign.Client
import feign.okhttp.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfig {

    @Bean
    fun feignClient(): Client {
        return OkHttpClient();
    }
}
