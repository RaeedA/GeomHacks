package src.GroSpace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GroSpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroSpaceApplication.class, args);
	}
	
	@GetMapping("/validate")
	public boolean validatePlant(@RequestParam(value = "plant", defaultValue = "") String plant)
	{
	    return false;
	}
}
