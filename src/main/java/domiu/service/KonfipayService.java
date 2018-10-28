package domiu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.catpay.domiu.konfipay.dto.DirectDebitType;
import domiu.RequestResponseLoggingInterceptor;
import domiu.dto.Credentials;
import domiu.dto.PaymentTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class KonfipayService {

    private String endpointBasePath = "https://portal.konfipay.de/";

    private String paymentSuffix = "api/v2.6/Payment/simple";

    private String loginTokenSuffix = "api/Login/Token";

    private String kontoUebersicht = "/api/v2.6/Payment/Status?Start={start}&End={end}";

    private String uuid = "e70a447575084600b0c72112c58319c0";


    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    @Autowired
    private LoginTokenService loginTokenService;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter());
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        restTemplate.getMessageConverters()
                .add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

        objectMapper = new ObjectMapper();
    }

    public ResponseEntity<String> erstelleLastschrift(DirectDebitType directDebitType) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        String a = PaymentTemplate.TEMPLATE;
        String requestString = a.replaceAll("##Name##", directDebitType.getInitPtyCreditor().getName());
        requestString = requestString.replaceAll("##DEBITOR##", directDebitType.getTransaction().get(0).getOthrPtyDebitor().getName());
        requestString = requestString.replace("##IBANABSENDER##", directDebitType.getInitPtyCreditor().getIBAN());
        requestString = requestString.replace("##IBANENPOFAENGER##", directDebitType.getTransaction().get(0).getOthrPtyDebitor().getIBAN());
        requestString = requestString.replace("##Amount##", directDebitType.getTransaction().get(0).getAmount().getValue().toString());
        requestString = requestString.replace("##Verwendungszweck##", directDebitType.getTransaction().get(0).getPurpose());
        headers.set("Authorization", "Bearer " + loginTokenService.getLoginToken());
        HttpEntity<String> entityReq = new HttpEntity<>(requestString, headers);
        System.out.println("Entity " + requestString);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.postForEntity(endpointBasePath + paymentSuffix, entityReq, String.class);
        } catch (HttpClientErrorException e) {

            System.out.println("body  " + new String(e.getResponseBodyAsByteArray(), Charset.forName("UTF-8")));
        }
        return response;

    }

    public void createUebersicht() {
        Map<String, Object> params = new HashMap<>();
        params.put("start", "26.10.2018");
        params.put("end", "28.10.2018");
//        params.put("iban", "DE62650700240021982400");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + loginTokenService.getLoginToken());
        HttpEntity<String> entityReq = new HttpEntity<>(headers);
        ResponseEntity<String> forObject = restTemplate.exchange(endpointBasePath + kontoUebersicht, HttpMethod.GET, entityReq, String.class, params);
        System.out.println("body  " + forObject);
    }
}
