package domiu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.catpay.domiu.konfipay.dto.DirectDebitType;
import domiu.RequestResponseLoggingInterceptor;
import domiu.dto.Credentials;
import domiu.dto.PaymentTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@Component
public class KonfipayService {

    private String endpointBasePath = "https://portal.konfipay.de/";

    private String paymentSuffix = "api/v2.6/Payment/simple";

    private String loginTokenSuffix = "api/Login/Token";

    private String uuid = "e70a447575084600b0c72112c58319c0";


    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    @Autowired
    private LoginTokenService loginTokenService;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors( Collections.singletonList(new RequestResponseLoggingInterceptor()) );
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter());
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        restTemplate.getMessageConverters()
                .add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

        objectMapper = new ObjectMapper();
    }

    public String getLoginToken() throws IOException {

        Credentials credentials = new Credentials();
        credentials.setUUID(uuid);
        credentials.setUsername("moneisz@gnubis.de");
        credentials.setPassword("5671f7ca5671f7ca");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
        String data = "{\"UUID\":\"e70a447575084600b0c72112c58319c0\", \"Username\":\"moneisz@gnubis.de\"" +
                ", \"Password\":\"5671f7ca5671f7ca\"}";
        HttpEntity<String> entity = new HttpEntity<>(data, headers);
        ResponseEntity<byte[]> stringResponseEntity = restTemplate.postForEntity(endpointBasePath + loginTokenSuffix, entity, byte[].class);
        String s = new String(stringResponseEntity.getBody(), "UTF-8").replaceAll("\\s+", "").replaceAll(" ", "");
        ResponseEntity<String> stringResponseEntity2 = restTemplate.postForEntity(endpointBasePath + loginTokenSuffix, entity,String.class);
        String d = s.replaceAll("\"\"","");
        int i = s.indexOf("a c c e s s _ t o k e n")+1;
        int i34 = s.indexOf("token")+1;
        int i2 = s.indexOf("t o")+1;
        int i223 = s.indexOf("\\s")+1;
        System.out.println(i);
        System.out.println(i2);
        String replace = StringUtils.replace(d, " ", "");
//        LoginToken loginToken = objectMapper.readValue(s, LoginToken.class);
        String substring = s.substring(s.charAt(i), s.charAt(i2));
        return s;
    }

    public ResponseEntity<String> erstelleLastschrift(DirectDebitType directDebitType) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        String a = PaymentTemplate.TEMPLATE;
        String requestString = a.replaceAll("##Name##", "Test");
        requestString = requestString.replace("##IBANABSENDER##",directDebitType.getInitPtyCreditor().getIBAN());
        requestString = requestString.replace("##IBANENPOFAENGER##",directDebitType.getTransaction().get(0).getOthrPtyDebitor().getIBAN());
        requestString = requestString.replace("##Amount##",directDebitType.getTransaction().get(0).getAmount().getValue().toString());
        requestString = requestString.replace("##Verwendungszweck##",directDebitType.getTransaction().get(0).getPurpose());
        headers.set("Authorization", "Bearer "+loginTokenService.getLoginToken());
        HttpEntity<String> entityReq = new HttpEntity<>(requestString,headers);
        System.out.println("Entity "+requestString);
        ResponseEntity<String> response = null;
        try{
            response = restTemplate.postForEntity(endpointBasePath+paymentSuffix, entityReq, String.class);
        }
        catch (HttpClientErrorException e){

            System.out.println("body  "+new String(e.getResponseBodyAsByteArray(), Charset.forName("UTF-8")));
        }
        return response;
    }
}
