package domiu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.catpay.domiu.konfipay.dto.DirectDebitType;
import de.catpay.domiu.konfipay.dto.InitPtyCreditorType;
import domiu.RequestResponseLoggingInterceptor;
import domiu.dto.Credentials;
import domiu.dto.PaymentTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class KonfipayService {

    private String endpointBasePath = "https://portal.konfipay.de/";

    private String paymentSuffix = "api/Payment/simple";

    private String loginTokenSuffix = "api/Login/Token";

    private String uuid = "e70a447575084600b0c72112c58319c0";


    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors( Collections.singletonList(new RequestResponseLoggingInterceptor()) );
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter());
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());

        objectMapper = new ObjectMapper();
    }

    public String getLoginToken() throws JsonProcessingException {

        Credentials credentials = new Credentials();
        credentials.setUUID(uuid);
        credentials.setUsername("moneisz@gnubis.de");
        credentials.setPassword("5671f7ca5671f7ca");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String data = "{\"UUID\":\"e70a447575084600b0c72112c58319c0\", \"Username\":\"moneisz@gnubis.de\"" +
                ", \"Password\":\"5671f7ca5671f7ca\"}";
        HttpEntity<String> entity = new HttpEntity<>(data, headers);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(endpointBasePath + loginTokenSuffix, entity, String.class);
        return stringResponseEntity.getBody();
    }

    public ResponseEntity<String> erstelleLastschrift(String loginToken, DirectDebitType directDebitType) throws JsonProcessingException {
        System.out.println("Token = "+loginToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        String a = PaymentTemplate.TEMPLATE;
        String requestString = a.replaceAll("##Name##", "Test");
        requestString = requestString.replace("##IBANABSENDER##",directDebitType.getInitPtyCreditor().getIBAN());
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE4NyIsIm5hbWUiOiJtb25laXN6QGdudWJpcy5kZSIsInV1aWQiOiJPbmhjSU0wZnEvemxkaDhJZWpFTDBGUytvVlB5bDc4T0IwK200TVA1eEJndy9KWUp2QVRzYnhtVVVyaEpDQXVjIiwic3lzYWRtaW4iOiJGYWxzZSIsIkViaWNzRW5hYmxlZCI6IkZhbHNlIiwiU3J6RW5hYmxlZCI6IkZhbHNlIiwiUGF5UGFsRW5hYmxlZCI6IlRydWUiLCJBcGlDb21tdW5pY2F0aW9uRW5hYmxlZCI6IlRydWUiLCJQYXltZW50RXhlY3V0aW9uQXBpRW5hYmxlZCI6IlRydWUiLCJhZG1pbiI6IlRydWUiLCJyb2xlIjoiQ3VzdG9tZXIiLCJleHAiOjE1NDA2NDIxNDh9.21gBdXBLOosPKMTs19OmWg9glDo1N9evaocm_Ozu--s");
        HttpEntity<String> entityReq = new HttpEntity<>("<Document xmlns=\"urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3 windata.ccft.1.3.xsd\">\n" +
                "\t<CreditTransfer>\n" +
                "\t\t<InitPtyDebitor>\n" +
                "\t\t\t<Name>Alice</Name>\n" +
                "\t\t\t<IBAN>DE62650700240021982400</IBAN>\n" +
                "\t\t\t<Id>ABCDEFG</Id>\n" +
                "\t\t</InitPtyDebitor>\n" +
                "\t\t<Transaction>\n" +
                "\t\t\t<Amount Ccy=\"EUR\">123.45</Amount>\n" +
                "\t\t\t<Purpose>Alice CreditTransfer Initiation 1</Purpose>\n" +
                "\t\t\t<ExecutionDate>2018-10-10</ExecutionDate>\n" +
                "\t\t\t<EndToEndId>Alice EndToEndId 1</EndToEndId>\n" +
                "\t\t\t<OthrPtyCreditor>\n" +
                "\t\t\t\t<Name>Bob</Name>\n" +
                "\t\t\t\t<IBAN>DE62650700240021982400</IBAN>\n" +
                "\t\t\t</OthrPtyCreditor>\n" +
                "\t\t</Transaction>\n" +
                "\t\t<Transaction>\n" +
                "\t\t\t<Amount Ccy=\"EUR\">234.56</Amount>\n" +
                "\t\t\t<Purpose>Alice CreditTransfer Initiation 2</Purpose>\n" +
                "\t\t\t<ExecutionDate>2018-10-15</ExecutionDate>\n" +
                "\t\t\t<EndToEndId>Alice EndToEndId 2</EndToEndId>\n" +
                "\t\t\t<OthrPtyCreditor>\n" +
                "\t\t\t\t<Name>Bob1</Name>\n" +
                "\t\t\t\t<IBAN>DE62650700240021982400</IBAN>\n" +
                "\t\t\t</OthrPtyCreditor>\n" +
                "\t\t</Transaction>\n" +
                "\t</CreditTransfer>\n" +
                "\t<DirectDebit>\n" +
                "\t\t<LocalInstrument>CORE</LocalInstrument>\n" +
                "\t\t<InitPtyCreditor>\n" +
                "\t\t\t<Name>Bob</Name>\n" +
                "\t\t\t<IBAN>DE62650700240021982400</IBAN>\n" +
                "\t\t\t<Id>GFEDCBA</Id>\n" +
                "\t\t\t<CreditorId>DE23ABCDEFG</CreditorId>\n" +
                "\t\t</InitPtyCreditor>\n" +
                "\t\t<Transaction>\n" +
                "\t\t\t<Amount Ccy=\"EUR\">543.21</Amount>\n" +
                "\t\t\t<Purpose>Bob DirectDebit Initiation 1</Purpose>\n" +
                "\t\t\t<ExecutionDate>2018-10-10</ExecutionDate>\n" +
                "\t\t\t<EndToEndId>Bob EndToEndId 1</EndToEndId>\n" +
                "\t\t\t<OthrPtyDebitor>\n" +
                "\t\t\t\t<Name>Alice</Name>\n" +
                "\t\t\t\t<IBAN>DE62650700240021982400</IBAN>\n" +
                "\t\t\t\t<MandateData>\n" +
                "\t\t\t\t\t<MandateId>MNDID123456</MandateId>\n" +
                "\t\t\t\t\t<MandateDate>2018-10-11</MandateDate>\n" +
                "\t\t\t\t\t<Sequence>RCUR</Sequence>\n" +
                "\t\t\t\t</MandateData>\n" +
                "\t\t\t</OthrPtyDebitor>\n" +
                "\t\t</Transaction>\n" +
                "\t\t<Transaction>\n" +
                "\t\t\t<Amount Ccy=\"EUR\">654.32</Amount>\n" +
                "\t\t\t<Purpose>Bob DirectDebit Initiation 2</Purpose>\n" +
                "\t\t\t<ExecutionDate>2018-10-15</ExecutionDate>\n" +
                "\t\t\t<EndToEndId>Bob EndToEndId 2</EndToEndId>\n" +
                "\t\t\t<OthrPtyDebitor>\n" +
                "\t\t\t\t<Name>Alice1</Name>\n" +
                "\t\t\t\t<IBAN>DE62650700240021982400</IBAN>\n" +
                "\t\t\t\t<MandateData>\n" +
                "\t\t\t\t\t<MandateId>MNDID123457</MandateId>\n" +
                "\t\t\t\t\t<MandateDate>2018-10-16</MandateDate>\n" +
                "\t\t\t\t\t<Sequence>FNAL</Sequence>\n" +
                "\t\t\t\t</MandateData>\n" +
                "\t\t\t</OthrPtyDebitor>\n" +
                "\t\t</Transaction>\n" +
                "\t</DirectDebit>\n" +
                "</Document>", headers);
//        System.out.println("Entity "+entityReq.toString());
        System.out.println("Entity "+requestString);
        ResponseEntity<String> response = restTemplate.postForEntity(endpointBasePath+paymentSuffix, entityReq, String.class);
        return response;
    }
}
