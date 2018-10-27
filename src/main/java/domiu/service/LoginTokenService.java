package domiu.service;

import domiu.RequestResponseLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class LoginTokenService {

    private String loginEndpoint = "https://tokengetterst18.herokuapp.com/";

    RestTemplate restTemplate;

    @PostConstruct
    public void init(){
        restTemplate = new RestTemplate();

//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory()));
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
    }

    public String getLoginToken(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(loginEndpoint, String.class);
        return forEntity.getBody();
    }
}
