package src.GroSpace;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RequestInitializer {
	
	static JSONObject APIResponse;
	static Scanner input = new Scanner(System.in);
	
	/*public static void main(String [] args) {
	System.out.println("GroSpace by Luca and Raeed, Geomhacks Submission 2020");
	System.out.println("--------------\n");

	System.out.println("Enter Length of Space in Ft");
	float length = input.nextFloat();

	System.out.println("\nEnter Width of Space in Ft");
	float width = input.nextFloat();

	System.out.println("\nEnter Height of Space in Ft");
	float height = input.nextFloat();

	System.out.println("\nWhich Plant Would You Like to Grow?");
	System.out.println("(Arugula, Kale, or Lettuce)");
	String plant = input.next();
	while (!(plant.equalsIgnoreCase("arugula") || plant.equalsIgnoreCase("kale") || plant.equalsIgnoreCase("lettuce"))) {
		System.out.println("\nPlease Enter a Valid Plant!");
		System.out.println("\nWhich Plant Would You Like to Grow?");
		System.out.println("(Arugula, Kale, or Lettuce)");
		plant = input.next();
	}
	calculate(length, width, height, plant, moduleCalculator());
	}*/
	
    public RequestInitializer(int length, int width, int height) {
        Request r = new Request();
        r.addData(height, width, length, 2, 4, 3);
        APIResponse = r.sendRequest();
    }
    
    public String getSVG() {
		return (String) APIResponse.get("svgs");
	}
    
    public String getImage()
    {
        return (String)( (JSONObject)( (JSONArray)APIResponse.get( "images" ) ).get( 0 ) ).get("data");
    }
    
    public long moduleCalculator() {
    	long leftovers = (long) APIResponse.get("lenLeftovers");
    	return 10000-leftovers;
    }
    
    
    public String WhperMonth() {
    	DecimalFormat df = new DecimalFormat("##.##");
    	df.setRoundingMode(RoundingMode.DOWN);
    	double result = (((moduleCalculator() * ((4*12) * (3*12))) / 1550.0) * 3500.0) / 12.0;
    	return df.format(result);
    }

    public String waterPerMonth() {
    	DecimalFormat df = new DecimalFormat("##.##");
    	df.setRoundingMode(RoundingMode.DOWN);
    	double result = (((moduleCalculator() * ((4*12) * (3*12))) / 1550.0) * 180.0) / 3.785;
    	return df.format(result);
    	
    }

    public static boolean isPlant(String plant) {
    	return plant.equalsIgnoreCase("arugula") || plant.equalsIgnoreCase("kale") || plant.equalsIgnoreCase("lettuce");
    	}
    
    /*public static void calculate(float length, float width, float height, String plant, int modules) {
    	System.out.println(moduleCalculator() + " Modules Will Fit in Your Space!");
    	System.out.println("\nYour Facility Will Use " + WhperMonth(length, width, modules) + " Watt Hours Per Month");
    	System.out.println("\nYour Facility Will Use " + waterPerMonth(length, width, modules) + " Gallons of Water Per Month.");
    }*/
}