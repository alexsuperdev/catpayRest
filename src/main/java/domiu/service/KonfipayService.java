package domiu.service;

import de.catpay.domiu.konfipay.dto.CustomerCreditTransferInitiationV03;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KonfipayService {

    private String endpoint = "https://portal.konfipay.de/api";

    public void erstelleLastschrift(CustomerCreditTransferInitiationV03 customerCreditTransferInitiationV03) {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/spring-rest/foos";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
    }
}
