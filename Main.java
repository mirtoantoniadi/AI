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

        while(!OpenList.isEmpty()){ //while the open list isn t empty continue(still have a state to explore)

            State Curr=null;

            
            for (State state : OpenList) {                         //returns a negative integer, zero, or a positive integer 
                                                                   //as this object is less than, equal to, or greater than the specified object
                if (Curr == null || state.compareTo(Curr) < 0) {
                Curr = state; //find the state with the lower cost(f)
                }
            }

            
            
            OpenList.remove(Curr); //remove it
            

            if(Curr.getG() > sum) { 
                System.out.println("Total time exceeded "+ sum +" The execution is terminated.");
                return;
            }

            if(Curr.isFinal()) { //If its the final state break 
                EndState = Curr;
                break;
            }

            
            for(State child : Curr.getChildren()) { //find all the childrens
                
                //we have to check if we have already explore this state 
                if(!ClosedList.contains(child) && !OpenList.contains(child)) {
                    
                    child.setFather(Curr);
                    child.evaluate();
                    
                    OpenList.add(child);
                }
            }
            ClosedList.add(Curr);

        }
        if (EndState != null) {
            
            LinkedList<State> path = new LinkedList<>();
            State current = EndState;
        
            // construct the path from end to start 
            while (current != null) {
                path.addFirst(current);
                current = current.getFather();
            }
        
            // iterate through the LinkedList and print each state
            path.forEach(State::print);
        }else{
            System.out.println("Didn t find a solution");
        }
        

    }

       
}
