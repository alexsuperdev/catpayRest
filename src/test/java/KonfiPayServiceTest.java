import com.fasterxml.jackson.core.JsonProcessingException;
import de.catpay.domiu.konfipay.dto.*;
import domiu.service.KonfipayService;
import domiu.service.LoginTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {KonfipayService.class, LoginTokenService.class})
public class KonfiPayServiceTest {

    @Autowired
    private KonfipayService konfipayService;

    @Autowired
    private LoginTokenService loginTokenService;

    @Test
    public void testZahlung() throws IOException {
        DirectDebitType directDebitType = new DirectDebitType();
//        String loginToken = konfipayService.getLoginToken().replaceAll("\\s+","").replaceAll(" ","");;
        InitPtyCreditorType initPtyCreditorType = new InitPtyCreditorType();
        initPtyCreditorType.setIBAN("DE62650700240021982400");
        initPtyCreditorType.setName("Test");
        directDebitType.setInitPtyCreditor(initPtyCreditorType);
        InitPtyCreditorType der = new InitPtyCreditorType();
        DirectDebitTransaction dsdf = new DirectDebitTransaction();
        AmountAndCurrencyType amountAndCurrencyType = new AmountAndCurrencyType();
        amountAndCurrencyType.setValue(BigDecimal.valueOf(100L));
        dsdf.setAmount(amountAndCurrencyType);
        dsdf.setPurpose("Test");
        OthrPtyDebitorType test = new OthrPtyDebitorType();
        test.setIBAN("DE62650700240021982400");
        dsdf.setOthrPtyDebitor(test);
        dsdf.setPurpose("Tedteegd");
        directDebitType.getTransaction().add(dsdf);

        konfipayService.erstelleLastschrift(directDebitType);
    }


}
