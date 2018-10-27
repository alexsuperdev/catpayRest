package domiu.service;

import de.catpay.domiu.konfipay.dto.CustomerCreditTransferInitiationV03;
import domiu.dto.Client;
import domiu.dto.LoginRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class KonfipayService {

    private String endpointBasePath = "https://portal.konfipay.de/";

    private String paymentSuffix = "api/Payment";

    private String loginTokenSuffix = "api/Login/Token";

    private String uuid = "e70a447575084600b0c72112c58319c0";

    private RestTemplate restTemplate;

    public void init() {
        restTemplate = new RestTemplate();
    }

    public String getLoginToken() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUUID(uuid);
        loginRequest.setUsername("moneisz@gnubis.de");
        loginRequest.setPassword("5671f7ca5671f7ca");
        Client client = new Client();
        client.setName("Client01");
        client.setVersion("1.0.0.0");

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(endpointBasePath, loginRequest, String.class);
        return stringResponseEntity.getBody();
    }

    public ResponseEntity<String> erstelleLastschrift(String loginToken, CustomerCreditTransferInitiationV03 customerCreditTransferInitiationV03) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+loginToken);

        HttpEntity<CustomerCreditTransferInitiationV03> entityReq = new HttpEntity<>(customerCreditTransferInitiationV03, headers);
        ResponseEntity<String> response
                = restTemplate.postForEntity(endpointBasePath, customerCreditTransferInitiationV03, String.class);
        return response;
    }
}
