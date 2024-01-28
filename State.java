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
}
	
