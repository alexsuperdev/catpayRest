package domiu.api;

import domiu.dto.Auftrag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class FrontConnector {
	
	@PostMapping
	public void createAntrag(@RequestBody Auftrag auftrag) {
		
	}
	

}
