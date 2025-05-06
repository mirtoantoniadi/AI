import java.util.*; 

public class State implements Comparable<State>
{
	private int f, h, g;
	private State father;
	private int totalTime;
	private List<Integer> RightList; 
	private List<Integer> LeftList; 
    	private boolean lampOnRight; 
	List<Integer> initialTimes;
	
	
    
	
	//constructor
    	public State(List<Integer> initialTimes) { //takes the times and puts them in RightList
        	this.RightList = new ArrayList<>(initialTimes);
        	this.LeftList = new ArrayList<>();
        	this.f = 0;
        	this.h = 0;
        	this.g = 0;
        	this.father = null;
        	this.totalTime = 0;
		this.lampOnRight=true; //true on right and false on left
		this.initialTimes=initialTimes;

    }

	//copy constructor
	public State(State s) {
		this.RightList = new ArrayList<>(s.RightList);
		this.LeftList = new ArrayList<>(s.LeftList);
		this.f = s.f;
		this.h = s.h;
		this.g = s.g;
		this.father = s.father; 
		this.totalTime = s.totalTime;
		this.lampOnRight = s.lampOnRight;
		this.initialTimes=s.initialTimes;
	}


	public List<Integer> getRightList() {
        	return new ArrayList<>(RightList);
    	}	

    	public void setRightList(List<Integer> RightList) {
        	this.RightList = new ArrayList<>(RightList);
    	}

    	public List<Integer> getLeftList() {
        	return new ArrayList<>(LeftList);
    	}

    	public void setLeftList(List<Integer> LeftList) {
        	this.LeftList = new ArrayList<>(LeftList);
    	}
	
	public int MaxH() { //heuristic finds the max of the right list
		int maxTime = Integer.MIN_VALUE; //minimum possible value for type int
		for (int time : RightList) {
			if (time > maxTime) {
				maxTime = time;
			}
		}
		return maxTime;
	}
	public int getF() 
	{
		return this.f;
	}
	
	public int getG() 
	{
		return this.g;
	}
	
	public int getH() 
	{
		return this.h;
	}
	
	public State getFather()
	{
		return this.father;
	}
	
	public void setF(int f)
	{
		this.f = f;
	}
	
	public void setG(int g)
	{
		this.g = g;
	}
	
	public void setH(int h)
	{
		this.h = h;
	}
	
	public void setFather(State f)
	{
		this.father = f;
	}
	
	public int getTotalTime() 
	{
		return this.totalTime;
	}
	
	public void setTotalTime(int time)
	{
		this.totalTime = time;
	}
	
	public void evaluate() //calculate f
	{
		this.h=MaxH(); 
		this.f=this.h+this.g;
	}
	
	public void print() {
		StringBuilder output = new StringBuilder();
	
		//construct the string for the left side if it is not empty
		if (!LeftList.isEmpty()) {
			StringBuilder leftStrBuilder = new StringBuilder("Left side: ");
			for (Integer time : LeftList) {
				leftStrBuilder.append(time).append(", ");
			}
			//remove the last comma and space.
			leftStrBuilder.delete(leftStrBuilder.length() - 2, leftStrBuilder.length());
			output.append(leftStrBuilder).append(" ");
		}

		//construct the string for the right side if it is not empty
		if (!RightList.isEmpty()) {
			StringBuilder rightStrBuilder = new StringBuilder("Right side: ");
			for (Integer time : RightList) {
				rightStrBuilder.append(time).append(", ");
			}
			//remove the last comma and space
			rightStrBuilder.delete(rightStrBuilder.length() - 2, rightStrBuilder.length());
			output.append(rightStrBuilder).append(" ");
		}
	
		//compose the torch position string and append it
		output.append("Torch position: ").append(lampOnRight ? "Right" : "Left").append(" ");
	
		//create the elapsed time string and append it.
		output.append("Time taken: ").append(this.getG());
	
		//output the complete status of the crossing.
		System.out.println(output);
	}

	
	public ArrayList<State> getChildren() {
    		ArrayList<State> children = new ArrayList<>();
    
    		if (lampOnRight) {
        		for (int i = 0; i < RightList.size(); i++) {
            			for (int j = i + 1; j < RightList.size(); j++) {
                		List<Integer> newRightList = new ArrayList<>(RightList);
                		List<Integer> newLeftList = new ArrayList<>(LeftList);
					

                		Integer item1 = newRightList.get(i);
                		Integer item2 = newRightList.get(j);

                		newLeftList.add(item1);
                		newLeftList.add(item2);

                		if (j > i) { //should remove the bigger number
                    		newRightList.remove(j);
                    		newRightList.remove(i);
             			} else {
                    		newRightList.remove(i);
                    		newRightList.remove(j);
                		}
                
                	
				State child = new State(this);
				child.setRightList(newRightList); 
				child.setLeftList(newLeftList);   
				child.setG(child.getG() + Math.max(item1, item2)); 
				totalTime+=Math.max(item1, item2);
				child.lampOnRight = !this.lampOnRight;
				children.add(child);
                	
            		}
        		}
    		} else {
        		for (int i = 0; i < LeftList.size(); i++) {
         			List<Integer> newRightList = new ArrayList<>(RightList);
            			List<Integer> newLeftList = new ArrayList<>(LeftList);
				

            			Integer item = newLeftList.get(i);
            			newRightList.add(item);
            			newLeftList.remove(i);

				State child = new State(this);
				child.setRightList(newRightList);
				child.setLeftList(newLeftList);   
				child.setG(child.getG() + item); 
				totalTime+=item;
				child.lampOnRight = !this.lampOnRight;
            			children.add(child);
        		}
    		}

    		return children;
		
       
	}	
	
	public boolean isFinal() {
		return RightList.isEmpty() && lampOnRight==false; //if all the persons and the lamp are on the left side
	} 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj){ 
			return true;
		} 
		if (obj == null || getClass() != obj.getClass()) {  
			return false;
		}
		State state = (State) obj; 
		return lampOnRight == state.lampOnRight && 
				Objects.equals(LeftList, state.LeftList) &&
				Objects.equals(RightList, state.RightList);
		}
	
	@Override
    	public int hashCode() {
		return Objects.hash(lampOnRight, LeftList, RightList);
	}
	
	@Override
    public int compareTo(State s)
    {
        return Double.compare(this.f, s.getF()); // compare based on the heuristic score.
    }
}
