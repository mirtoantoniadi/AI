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
}
	
