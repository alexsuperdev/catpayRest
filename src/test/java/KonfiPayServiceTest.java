import de.catpay.domiu.konfipay.dto.CustomerCreditTransferInitiationV03;
import domiu.service.KonfipayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KonfipayService.class)
public class KonfiPayServiceTest {

    @Autowired
    private KonfipayService konfipayService;

    @Test
    public void testZahlung(){
        CustomerCreditTransferInitiationV03 customerCreditTransferInitiationV03 = new CustomerCreditTransferInitiationV03();
        String loginToken = konfipayService.getLoginToken();
        konfipayService.erstelleLastschrift(customerCreditTransferInitiationV03);
    }
}
