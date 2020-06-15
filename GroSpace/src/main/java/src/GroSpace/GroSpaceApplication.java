package src.GroSpace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GroSpaceApplication {

    public static RequestInitializer request;
    
	public static void main(String[] args) {
	    //request = new RequestInitializer(50,25,10);
		SpringApplication.run(GroSpaceApplication.class, args);
	}
	
	@GetMapping("/validate")
	public boolean validatePlant(@RequestParam(value = "plant", defaultValue = "") String plant)
	{
	    return RequestInitializer.isPlant( plant );
	}
	
	@GetMapping("/create")
	public void createRequest(@RequestParam(value="width", defaultValue = "") String width, @RequestParam(value="length", defaultValue = "") String length, @RequestParam(value="height", defaultValue = "") String height)
	{
	    int w = Integer.parseInt( width );
	    int l = Integer.parseInt( length );
	    int h = Integer.parseInt( height );
	    request = new RequestInitializer(l,w,h);
	}
	
	@GetMapping("/image")
	public String getImage()
	{
	    return request.getImage();
	}
	
	@GetMapping("/number")
	public String numberModules()
	{
	    return request.moduleCalculator() + " modules!";
	}
	
	@GetMapping("/electricity")
    public String electricityAmount()
    {
        return request.WhperMonth() + " watt hours per month!";
    }
	
	@GetMapping("/water")
	public String waterAmount()
	{
	    return request.waterPerMonth() + " liters per month!";
	}
}
