
package domiu.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.catpay.domiu.konfipay.dto.*;
import domiu.dto.Antrag;
import domiu.dto.Nutzer;
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
                            + "an_beschreibung, " + "an_lohn,  an_anzahlbez, an_anzahlbezverfuegbar ) "
                            + "VALUES (?,?,?,?,?,?,?) ",
                    antrag.getPerson(), antrag.getAuftragnehmer(), antrag.getThema(), antrag.getBeschreibung(),
                    antrag.getBetrag(), 0, antrag.getWiederholung());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity.ok().body("");

    }

    @GetMapping(value = "v1/api/getAuftraege")
    public ResponseEntity<List<Antrag>> getAuftraege(@RequestParam String userId) {
        List<Antrag> auftraege =

                jdbcTemplate.query("select * from antrag where an_auftragnehmer_id = ? and an_anzahlbez < an_anzahlbezverfuegbar ",
                        (rs, rowNum) -> new Antrag(rs.getInt("an_auftragid"), rs.getString("an_auftraggeber_id"),
                                rs.getString("an_auftragnehmer_id"), rs.getString("an_thema"),
                                rs.getString("an_beschreibung"), rs.getDouble("an_lohn"), rs.getInt("an_anzahlbez"),
                                rs.getInt("an_anzahlbezverfuegbar"), rs.getString("an_letztezahlung")), userId)
                        .stream().collect(Collectors.toList());
        return ResponseEntity.ok(auftraege);
    }
//
//    @GetMapping(value = "v1/api/getAuftraegeFuerAuftraggeber")
//    public ResponseEntity<List<Antrag>> getAuftraegeFuerAuftraggeber(@RequestParam String auftraggeberId) {
//        List<Antrag> auftraege =
//
//                jdbcTemplate.query("select * from antrag where an_auftraggeber_id  = ?",
//                        (rs, rowNum) -> new Antrag(rs.getString("an_auftragid"), rs.getString("an_auftraggeber_id"),
//                                rs.getString("an_auftragnehmer_id"), rs.getString("an_thema"),
//                                rs.getString("an_beschreibung"), rs.getDouble("an_lohn"), rs.getInt("an_anzahlbez"),
//                                rs.getString("an_anzahlbezverfuegbar"), rs.getString("an_letztezahlung")),auftraggeberId)
//                        .stream().collect(Collectors.toList());
//        return ResponseEntity.ok(auftraege);
//    }

    @GetMapping(value = "v1/api/createZahlung")
    public ResponseEntity<String> createZahlung(@RequestParam Integer auftragid) throws JsonProcessingException {


        DirectDebitType directDebitType = new DirectDebitType();
        InitPtyCreditorType creditor = new InitPtyCreditorType();
//        if (zahlung.getAntrag() != null) {
//            creditor.setName(zahlung.getAntrag().getPerson());
//        } else {
        creditor.setName("Blubb");
//        }

        Integer anzahlbez = jdbcTemplate.queryForObject("select an_anzahlbez  from antrag where an_auftragid = ? ", Integer.class, auftragid);
        Integer anzahlbezverfuegbar = jdbcTemplate.queryForObject("select an_anzahlbezverfuegbar from antrag where an_auftragid = ? ", Integer.class, auftragid);
        Double betrag = jdbcTemplate.queryForObject("select an_lohn  from antrag where an_auftragid = ? ", Double.class, auftragid);
        if (anzahlbezverfuegbar <= anzahlbez) {
            return ResponseEntity.badRequest().body("Betrüger entdeckt");
        }
        creditor.setIBAN("DE62650700240021982400");
        creditor.setCreditorId("Maffay");
        directDebitType.setInitPtyCreditor(creditor);

        DirectDebitTransaction debitTransaction = new DirectDebitTransaction();
        AmountAndCurrencyType amountAndCurrencyType = new AmountAndCurrencyType();
        amountAndCurrencyType.setValue(BigDecimal.valueOf(betrag));
        debitTransaction.setAmount(amountAndCurrencyType);
        OthrPtyDebitorType tder = new OthrPtyDebitorType();
        tder.setIBAN("DE62650700240021982400");
        debitTransaction.setOthrPtyDebitor(tder);
        debitTransaction.setPurpose("CopyPAste");
        directDebitType.getTransaction().add(debitTransaction);//
        ResponseEntity<String> stringResponseEntity = konfipayService.erstelleLastschrift(directDebitType);
        System.out.println("Ende Kofipay response " + stringResponseEntity.getStatusCode());
        if (HttpStatus.CREATED.equals(stringResponseEntity.getStatusCode())) {
            System.out.println("Testst");
            try {
                jdbcTemplate.update("update antrag set an_anzahlbez = ?  where an_auftragid = ? ", anzahlbez + 1, auftragid);
                System.out.println(jdbcTemplate.toString());
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
