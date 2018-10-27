package domiu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class Config {

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory()));
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));

        return restTemplate;
    }
}
