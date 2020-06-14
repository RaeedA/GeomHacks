package src.GroSpace;
import java.util.Scanner;

public class Input {
    
    static Scanner input = new Scanner(System.in);
        public static void main(String [] args) {
        System.out.println("GroSpace by Luca and Raeed, Geomhacks Submission 2020");
        System.out.println("--------------\n");
        
        System.out.println("Enter Length of Space");
        double length = input.nextDouble();
        
        System.out.println("\nEnter Width of Space");
        double width = input.nextDouble();
        
        System.out.println("\nEnter Height of Space");
        double height = input.nextDouble();
        
        System.out.println("\nWhich Plant Would You Like to Grow?");
        System.out.println("(Arugula, Kale, or Lettuce)");
        String plant = input.next();
        while (!(plant.equalsIgnoreCase("arugula") || plant.equalsIgnoreCase("kale") || plant.equalsIgnoreCase("lettuce"))) {
            System.out.println("\nPlease Enter a Valid Plant!");
            System.out.println("\nWhich Plant Would You Like to Grow?");
            System.out.println("(Arugula, Kale, or Lettuce)");
            plant = input.next();
        }
    }
}