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

        if(args.length - 1 != size) { //match times and sizes
            System.out.println("The stated size of the list does not match the times given. Execution terminated.");
            return;
        }

        List<Integer> initialTimes = new ArrayList<>(); //create the list and add the times from args[]
        for(int i = 1; i < args.length; i++) {
            try {
                initialTimes.add(Integer.parseInt(args[i]));
            } catch(NumberFormatException e) {
                System.out.println("The definition: " + args[i] + " is not a valid number.");
                return;
            }
        }

        //we create the first state
        State FirstState=new State(initialTimes);
        
        //definition of the end state
        State EndState=null;

        List<State> OpenList = new ArrayList<>();
        Set<State> ClosedList = new HashSet<>();

        //add the first state in open list
        OpenList.add(FirstState);
        int sum = initialTimes.stream().mapToInt(Integer::intValue).sum();

       
}
