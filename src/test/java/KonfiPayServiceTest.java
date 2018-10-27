import com.fasterxml.jackson.core.JsonProcessingException;
import de.catpay.domiu.konfipay.dto.AmountAndCurrencyType;
import de.catpay.domiu.konfipay.dto.DirectDebitTransaction;
import de.catpay.domiu.konfipay.dto.DirectDebitType;
import de.catpay.domiu.konfipay.dto.InitPtyCreditorType;
import domiu.service.KonfipayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KonfipayService.class)
public class KonfiPayServiceTest {

    @Autowired
    private KonfipayService konfipayService;

    @Test
    public void testZahlung() throws JsonProcessingException {
        DirectDebitType directDebitType = new DirectDebitType();
        String loginToken = konfipayService.getLoginToken();
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
        directDebitType.getTransaction().add(dsdf);

        konfipayService.erstelleLastschrift(loginToken, directDebitType);
    }
}
