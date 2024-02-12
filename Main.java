import java.util.*;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1) { //if no definition is given it displays a message and terminates
            System.out.println("No definitions were given.To run the program you have to put the size of the list and next to it the times.The execution is terminated.");
            return;
        }

        int size; //the first definition is the size of the list
        try {
            size = Integer.parseInt(args[0]);
        } catch(NumberFormatException e) {
            System.out.println("The first definition: " + args[0] + " must be a number indicating the size of the list");
            return;
        }

       
}
