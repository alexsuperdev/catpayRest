
package domiu.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.catpay.domiu.konfipay.dto.*;
import domiu.dto.Antrag;
import domiu.dto.Nutzer;
import domiu.dto.Zahlung;
import domiu.service.KonfipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FrontConnector {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private KonfipayService konfipayService;

    @PostMapping(value = "v1/api/createAuftrag")
    public ResponseEntity<String> createAntrag(@RequestBody Antrag antrag) {

        try {

            jdbcTemplate.update(
                    "insert into antrag " + "(" + "an_auftraggeber_id, " + "an_auftragnehmer_id, " + "an_thema, "
                            + "an_beschreibung, " + "an_lohn, " + "an_anzahlbez, " + "an_anzahlbezverfuegbar ) "
                            + "VALUES (?,?,?,?,?,?,?) ",
                    antrag.getPerson(), antrag.getAuftragnehmer(), antrag.getThema(), antrag.getBeschreibung(),
                    antrag.getBetrag(), antrag.getWiederholung(), antrag.getWiederholung());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity.ok().body("");

    }

    @GetMapping(value = "v1/api/getAuftraege")
    public ResponseEntity<List<Antrag>> getAuftraege(@RequestParam String userId) {
        List<Antrag> auftraege =

                jdbcTemplate.query("select * from antrag",
                        (rs, rowNum) -> new Antrag(rs.getString("an_auftragid"), rs.getString("an_auftraggeber_id"),
                                rs.getString("an_auftragnehmer_id"), rs.getString("an_thema"),
                                rs.getString("an_beschreibung"), rs.getDouble("an_lohn"), rs.getInt("an_anzahlbez"),
                                rs.getString("an_anzahlbezverfuegbar"), rs.getString("an_letztezahlung")))
                        .stream().collect(Collectors.toList());
        // .forEach(x -> System.out.println(x.toString()));
        return ResponseEntity.ok(auftraege);
    }

    @PostMapping(value = "v1/api/createZahlung")
    public ResponseEntity<String> createZahlung(@RequestBody Zahlung zahlung) throws JsonProcessingException {

        DirectDebitType directDebitType = new DirectDebitType();
        InitPtyCreditorType creditor = new InitPtyCreditorType();
        if (zahlung.getAuftrag() != null){
            creditor.setName(zahlung.getAuftrag().getPerson());
        }
        else{
            creditor.setName("Blubb");
        }
        creditor.setIBAN("DE62650700240021982400");
        creditor.setCreditorId(zahlung.getAuftrag().getPerson());
        directDebitType.setInitPtyCreditor(creditor);

        DirectDebitTransaction debitTransaction = new DirectDebitTransaction();
        AmountAndCurrencyType amountAndCurrencyType = new AmountAndCurrencyType();
        amountAndCurrencyType.setValue(BigDecimal.valueOf(zahlung.getAuftrag().getBetrag()));
        debitTransaction.setAmount(amountAndCurrencyType);
        OthrPtyDebitorType tder = new OthrPtyDebitorType();
        tder.setIBAN("DE62650700240021982400");
        debitTransaction.setOthrPtyDebitor(tder);
        debitTransaction.setPurpose(zahlung.getAuftrag().getThema());
        directDebitType.getTransaction().add(debitTransaction);
        ResponseEntity<String> stringResponseEntity = konfipayService.erstelleLastschrift(directDebitType);
        if (HttpStatus.OK.equals(stringResponseEntity.getStatusCode())){

            try {

                jdbcTemplate.update(
                        "update antrag set an_anzahlbezverfuegbar  "
                                + "where  an_auftragid = ? ",
                        1);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }
        }
        return ResponseEntity.ok().body("okay");
    }

    @GetMapping(value = "v1/api/getStatus")
    // public ResponseEntity<String> getStaus(@RequestBody Status status) {
    public ResponseEntity<String> getStaus(String wert) {
        System.out.println("hello");
        jdbcTemplate
                .query("select nu_id ,nu_iban ,nu_bic,nu_rfid from nutzer",
                        (rs, rowNum) -> new Nutzer(rs.getString("nu_id")))
                .forEach(x -> System.out.println(x.toString()));

        return ResponseEntity.ok().body("helloworld");
    }

}
